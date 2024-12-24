package cz.cvut.docta.course.data.repository

import cz.cvut.docta.course.data.model.CourseEntity
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getAllCourses(): Flow<List<CourseEntity>>

}