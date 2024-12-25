package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.lesson.data.local.source.LessonLocalDataSource
import cz.cvut.docta.lesson.data.model.LessonEntity

class LessonRepositoryImpl(
    private val localSource: LessonLocalDataSource
) : LessonRepository {
    override suspend fun getSectionLessons(sectionId: Long): List<LessonEntity> {
        return localSource.getSectionLessons(sectionId = sectionId)
    }
}