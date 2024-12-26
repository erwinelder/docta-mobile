package cz.cvut.docta.lesson.data.local.source

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.lesson.data.local.dao.LessonDao
import cz.cvut.docta.lesson.data.mapper.toStepByStepLessonDetailsList
import cz.cvut.docta.lesson.data.mapper.toDefaultLessonDetailsList
import cz.cvut.docta.lesson.data.model.LessonDetails
import cz.cvut.docta.lesson.data.model.LessonDetailsStatistics
import cz.cvut.docta.lesson.data.model.LessonDetailsWithStatistics

class LessonLocalDataSourceImpl(
    private val dao: LessonDao
) : LessonLocalDataSource {

    override suspend fun getSectionLessons(sectionId: Long): List<LessonDetails> {
        val defaultLessons = dao.getDefaultLessons(sectionId = sectionId)
            .toDefaultLessonDetailsList()
        val stepByStepLessons = dao.getStepByStepLessons(sectionId = sectionId)
            .toStepByStepLessonDetailsList()

        return (defaultLessons + stepByStepLessons).sortedBy { it.orderNum }
    }

    override suspend fun getSectionLessonsWithStatistics(
        sectionId: Long
    ): List<LessonDetailsWithStatistics> {
        // TODO-LESSON: query statistics from database

        val defaultLessons = dao.getDefaultLessons(sectionId = sectionId)
            .toDefaultLessonDetailsList()
            .map { lesson ->
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
        val stepByStepLessons = dao.getStepByStepLessons(sectionId = sectionId)
            .toStepByStepLessonDetailsList()
            .map { lesson ->
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

fun lessonLocalDataSourceFactory(
    appLocalDatabase: AppLocalDatabase
): LessonLocalDataSourceImpl {
    return LessonLocalDataSourceImpl(dao = appLocalDatabase.lessonDao())
}