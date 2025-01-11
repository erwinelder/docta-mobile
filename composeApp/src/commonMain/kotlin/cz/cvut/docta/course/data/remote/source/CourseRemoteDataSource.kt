package cz.cvut.docta.course.data.remote.source

import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

interface CourseRemoteDataSource {

    suspend fun getUpdateTime(): Long?

    suspend fun saveUpdateTime(timestamp: Long)

    suspend fun getCoursesAfterTimestamp(timestamp: Long): List<CourseRemoteEntity>

}