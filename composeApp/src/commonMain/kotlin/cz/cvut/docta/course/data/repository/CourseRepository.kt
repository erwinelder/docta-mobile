package cz.cvut.docta.course.data.repository

import cz.cvut.docta.course.data.local.model.CourseEntity

interface CourseRepository {

    suspend fun getAllCourses(): List<CourseEntity>

    suspend fun getCourses(codes: List<String>): List<CourseEntity>

    suspend fun getCourse(courseCode: String): CourseEntity?

    suspend fun getCourseRemotely(courseCode: String): CourseEntity?

}