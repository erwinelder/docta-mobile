package cz.cvut.docta.lesson.data.local.source

import cz.cvut.docta.lesson.data.model.LessonEntity

interface LessonLocalDataSource {

    suspend fun getSectionLessons(sectionId: Long): List<LessonEntity>

}