package cz.cvut.docta.lesson.data.local.source

import cz.cvut.docta.lesson.data.model.LessonDetails
import cz.cvut.docta.lesson.data.model.LessonDetailsWithStatistics

interface LessonLocalDataSource {

    suspend fun getLessonType(lessonId: Long): String

    suspend fun getDefaultLesson(lessonId: Long): LessonDetails.Default?

    suspend fun getSectionLessons(sectionId: Long): List<LessonDetails>

    suspend fun getSectionLessonsWithStatistics(sectionId: Long): List<LessonDetailsWithStatistics>

}