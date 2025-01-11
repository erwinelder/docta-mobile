package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.core.data.utils.synchroniseData
import cz.cvut.docta.lesson.data.local.source.LessonLocalDataSource
import cz.cvut.docta.lesson.data.mapper.toLessonDetailsToSync
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetails
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetailsWithStatistics
import cz.cvut.docta.lesson.data.remote.source.LessonRemoteDataSource
import cz.cvut.docta.lesson.data.remote.model.LessonRemoteDetails

class LessonRepositoryImpl(
    private val localSource: LessonLocalDataSource,
    private val remoteSource: LessonRemoteDataSource
) : LessonRepository {

    private suspend fun synchroniseLessons(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getUpdateTime,
            remoteDataGetter = remoteSource::getLessonsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<LessonRemoteDetails>::toLessonDetailsToSync,
            localSynchroniser = localSource::synchroniseLessons
        )
    }

    override suspend fun getLessonType(courseCode: String, lessonId: Long): String {
        synchroniseLessons(courseCode = courseCode)
        return localSource.getLessonType(lessonId = lessonId)
    }

    override suspend fun getDefaultLesson(courseCode: String, lessonId: Long): LessonDetails.Default? {
        synchroniseLessons(courseCode = courseCode)
        return localSource.getDefaultLesson(lessonId = lessonId)
    }

    override suspend fun getSectionLessons(courseCode: String, sectionId: Long): List<LessonDetails> {
        synchroniseLessons(courseCode = courseCode)
        return localSource.getSectionLessons(sectionId = sectionId)
    }

    override suspend fun getSectionLessonsWithStatistics(
        courseCode: String,
        sectionId: Long
    ): List<LessonDetailsWithStatistics> {
        synchroniseLessons(courseCode = courseCode)
        return localSource.getSectionLessonsWithStatistics(sectionId = sectionId)
    }

}