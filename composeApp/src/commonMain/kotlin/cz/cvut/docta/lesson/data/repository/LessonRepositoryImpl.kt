package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.lesson.data.local.source.LessonLocalDataSource
import cz.cvut.docta.lesson.data.model.toDomainModel
import cz.cvut.docta.lesson.domain.model.Lesson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LessonRepositoryImpl(
    private val localSource: LessonLocalDataSource
) : LessonRepository {

    override suspend fun getSectionLessons(sectionId: Long): List<Lesson> {
        return localSource.getSectionLessons(sectionId)
            .map { entities -> entities.map { it.toDomainModel() } }
            .first()
    }
}