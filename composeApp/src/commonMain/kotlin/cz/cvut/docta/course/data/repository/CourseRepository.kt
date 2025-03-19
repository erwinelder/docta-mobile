package cz.cvut.docta.course.data.repository

import cz.cvut.docta.course.data.model.CourseDto

interface CourseRepository {

    suspend fun getCourses(codes: List<String>): List<CourseDto>

    suspend fun getCourse(courseCode: String): CourseDto?

    suspend fun getCourseRemotely(courseCode: String): CourseDto?

}