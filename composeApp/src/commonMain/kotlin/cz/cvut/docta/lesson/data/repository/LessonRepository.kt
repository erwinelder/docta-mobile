package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.lesson.data.local.model.LessonType
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetails
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetailsWithUserStats

interface LessonRepository {

    suspend fun getLessonType(courseCode: String, sectionId: Long, lessonId: Long): LessonType?

    suspend fun getDefaultLesson(courseCode: String, sectionId: Long, lessonId: Long): LessonDetails.Default?

    suspend fun getSectionLessons(courseCode: String, sectionId: Long): List<LessonDetails>

    suspend fun getSectionLessonsWithStatistics(courseCode: String, sectionId: Long): List<LessonDetailsWithUserStats>

}