package cz.cvut.docta.lesson.data.remote.source

import cz.cvut.docta.lesson.data.remote.model.LessonRemoteDetails

interface LessonRemoteDataSource {

    suspend fun getUpdateTime(courseCode: String): Long?

    suspend fun saveUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getLessonsAfterTimestamp(courseCode: String, timestamp: Long): List<LessonRemoteDetails>

}