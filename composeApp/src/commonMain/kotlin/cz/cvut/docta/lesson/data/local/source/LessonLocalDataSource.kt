package cz.cvut.docta.lesson.data.local.source

import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetails
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetailsWithStatistics

interface LessonLocalDataSource {

    suspend fun getUpdateTime(courseCode: String): Long?

    suspend fun saveUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseLessons(
        lessonsToSync: EntitiesToSynchronise<LessonDetails>,
        courseCode: String,
        timestamp: Long
    )

    suspend fun getLessonType(lessonId: Long): String

    suspend fun getDefaultLesson(lessonId: Long): LessonDetails.Default?

    suspend fun getSectionLessons(sectionId: Long): List<LessonDetails>

    suspend fun getSectionLessonsWithStatistics(sectionId: Long): List<LessonDetailsWithStatistics>

}