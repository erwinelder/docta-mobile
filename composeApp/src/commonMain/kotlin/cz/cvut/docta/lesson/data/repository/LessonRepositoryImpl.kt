package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.lesson.data.local.source.LessonLocalDataSource
import cz.cvut.docta.lesson.data.model.LessonDetails
import cz.cvut.docta.lesson.data.model.LessonDetailsWithStatistics

class LessonRepositoryImpl(
    private val localSource: LessonLocalDataSource
) : LessonRepository {

    override suspend fun getSectionLessons(sectionId: Long): List<LessonDetails> {
        return localSource.getSectionLessons(sectionId = sectionId)
    }

    override suspend fun getSectionLessonsWithStatistics(
        sectionId: Long
    ): List<LessonDetailsWithStatistics> {
        return localSource.getSectionLessonsWithStatistics(sectionId = sectionId)
    }

}