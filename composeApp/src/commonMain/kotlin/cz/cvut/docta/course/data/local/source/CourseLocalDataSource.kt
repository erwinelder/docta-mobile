package cz.cvut.docta.course.data.local.source

import cz.cvut.docta.course.data.model.CourseEntity
import kotlinx.coroutines.flow.Flow

interface CourseLocalDataSource {

    fun getAllCourses(): Flow<List<CourseEntity>>
    suspend fun getCourse(courseCode: String): CourseEntity?

}