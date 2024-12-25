package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.lesson.data.model.LessonEntity

interface LessonRepository {

    suspend fun getSectionLessons(sectionId: Long): List<LessonEntity>

}