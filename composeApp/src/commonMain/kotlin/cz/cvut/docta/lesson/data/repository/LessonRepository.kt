package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.lesson.data.model.LessonDto
import cz.cvut.docta.lesson.data.model.LessonWithProgressDto

interface LessonRepository {

    suspend fun getLessons(courseCode: String, sectionId: Int): List<LessonDto>

    suspend fun getLessonsWithProgress(courseCode: String, sectionId: Int): List<LessonWithProgressDto>

}