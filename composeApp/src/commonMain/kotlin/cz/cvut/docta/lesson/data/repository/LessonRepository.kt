package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.lesson.domain.model.Lesson

interface LessonRepository {

    suspend fun getSectionLessons(sectionId: Long): List<Lesson>

}