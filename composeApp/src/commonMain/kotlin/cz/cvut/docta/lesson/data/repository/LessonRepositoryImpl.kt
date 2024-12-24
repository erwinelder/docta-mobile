package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.lesson.data.local.source.LessonLocalDataSource
import cz.cvut.docta.lesson.domain.model.Lesson

class LessonRepositoryImpl(
    private val localSource: LessonLocalDataSource
) : LessonRepository {

    override suspend fun getSectionLessons(sectionId: Long): List<Lesson> {
        return emptyList() // TODO-LESSON
    }

}