package cz.cvut.docta.course.data.local.source

import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.course.data.local.model.CourseEntity

interface CourseLocalDataSource {

    suspend fun getUpdateTime(): Long?

    suspend fun saveUpdateTime(timestamp: Long)

    suspend fun synchroniseCourses(
        coursesToSync: EntitiesToSynchronise<CourseEntity>,
        timestamp: Long
    )

    suspend fun getAllCourses(): List<CourseEntity>

    suspend fun getCourse(courseCode: String): CourseEntity?

}