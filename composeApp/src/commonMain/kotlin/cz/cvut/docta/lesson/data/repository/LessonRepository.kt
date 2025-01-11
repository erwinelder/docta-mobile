package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetails
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetailsWithStatistics

interface LessonRepository {

    suspend fun getLessonType(courseCode: String, lessonId: Long): String

    suspend fun getDefaultLesson(courseCode: String, lessonId: Long): LessonDetails.Default?

    suspend fun getSectionLessons(courseCode: String, sectionId: Long): List<LessonDetails>

    suspend fun getSectionLessonsWithStatistics(courseCode: String, sectionId: Long): List<LessonDetailsWithStatistics>

}