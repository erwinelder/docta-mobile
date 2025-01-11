package cz.cvut.docta.lesson.data.local.source

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.core.data.local.dao.LocalUpdateTimeDao
import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.core.data.local.model.LocalUpdateTime
import cz.cvut.docta.core.data.model.TableName
import cz.cvut.docta.lesson.data.local.dao.LessonDao
import cz.cvut.docta.lesson.data.mapper.wrapInSealedClass
import cz.cvut.docta.lesson.data.mapper.toSealedStepByStepLessonDetails
import cz.cvut.docta.lesson.data.mapper.toSealedDefaultLessonDetails
import cz.cvut.docta.lesson.data.mapper.toDefaultLessonEntities
import cz.cvut.docta.lesson.data.mapper.toLessonEntities
import cz.cvut.docta.lesson.data.mapper.toStepByStepLessonEntities
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetails
import cz.cvut.docta.lesson.data.local.model.LessonDetailsStatistics
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetailsWithStatistics

class LessonLocalDataSourceImpl(
    private val lessonDao: LessonDao,
    private val updateTimeDao: LocalUpdateTimeDao
) : LessonLocalDataSource {

    override suspend fun getUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.Lesson.name, courseCode = courseCode
        )
    }

    override suspend fun saveUpdateTime(courseCode: String, timestamp: Long) {
        val updateTime = LocalUpdateTime(
            tableName = TableName.Lesson.name, courseCode = courseCode, updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun synchroniseLessons(
        lessonsToSync: EntitiesToSynchronise<LessonDetails>,
        courseCode: String,
        timestamp: Long
    ) {
        val lessonsToDelete = lessonsToSync.toDelete.toLessonEntities()
        val lessonsToUpsert = lessonsToSync.toUpsert.toLessonEntities()

        val defaultLessonsToUpsert = lessonsToSync.toUpsert.toDefaultLessonEntities()
        val stepByStepLessonsToUpsert = lessonsToSync.toUpsert.toStepByStepLessonEntities()

        lessonDao.deleteAndUpsertLessonsAndInheritedLessons(
            lessonsToDelete = lessonsToDelete,
            lessonsToUpsert = lessonsToUpsert,
            defaultLessonsToUpsert = defaultLessonsToUpsert,
            stepByStepLessonsToUpsert = stepByStepLessonsToUpsert
        )
        saveUpdateTime(courseCode = courseCode, timestamp = timestamp)
    }

    override suspend fun getLessonType(lessonId: Long): String {
        return lessonDao.getLessonType(lessonId = lessonId)
    }

    override suspend fun getDefaultLesson(lessonId: Long): LessonDetails.Default? {
        return lessonDao.getDefaultLesson(lessonId = lessonId)?.wrapInSealedClass()
    }

    override suspend fun getSectionLessons(sectionId: Long): List<LessonDetails> {
        val defaultLessons = lessonDao.getSectionDefaultLessons(sectionId = sectionId)
            .toSealedDefaultLessonDetails()
        val stepByStepLessons = lessonDao.getSectionStepByStepLessons(sectionId = sectionId)
            .toSealedStepByStepLessonDetails()

        return (defaultLessons + stepByStepLessons).sortedBy { it.orderNum }
    }

    override suspend fun getSectionLessonsWithStatistics(
        sectionId: Long
    ): List<LessonDetailsWithStatistics> {
        // TODO-LESSON: query statistics from database

        val defaultLessons = lessonDao.getSectionDefaultLessons(sectionId = sectionId)
            .toSealedDefaultLessonDetails()
            .map { lesson ->
                // temporary primitive mapper, since statistics are not yet implemented
                LessonDetailsWithStatistics.DefaultLesson(
                    sectionId = lesson.sectionId,
                    id = lesson.id,
                    orderNum = lesson.orderNum,
                    name = lesson.name,
                    difficulty = lesson.difficulty,
                    statistics = LessonDetailsStatistics(isDone = false),
                    type = lesson.type,
                    matchAllTags = lesson.matchAllTags
                )
            }
        val stepByStepLessons = lessonDao.getSectionStepByStepLessons(sectionId = sectionId)
            .toSealedStepByStepLessonDetails()
            .map { lesson ->
                // temporary primitive mapper, since statistics are not yet implemented
                LessonDetailsWithStatistics.StepByStepLesson(
                    sectionId = lesson.sectionId,
                    id = lesson.id,
                    orderNum = lesson.orderNum,
                    name = lesson.name,
                    difficulty = lesson.difficulty,
                    statistics = LessonDetailsStatistics(isDone = false),
                    description = lesson.description
                )
            }

        return (defaultLessons + stepByStepLessons).sortedBy { it.orderNum }
    }

}

fun lessonLocalDataSourceFactory(appLocalDatabase: AppLocalDatabase): LessonLocalDataSourceImpl {
    return LessonLocalDataSourceImpl(
        lessonDao = appLocalDatabase.lessonDao(),
        updateTimeDao = appLocalDatabase.localUpdateTimeDao()
    )
}