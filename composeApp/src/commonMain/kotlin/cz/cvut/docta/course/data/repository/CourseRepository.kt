package cz.cvut.docta.course.data.repository

import cz.cvut.docta.course.data.model.CourseDto
import cz.cvut.docta.course.data.model.CourseWithProgressDto

interface CourseRepository {

    suspend fun getCourses(codes: List<String>): List<CourseDto>

    suspend fun getCourse(code: String): CourseDto?

    suspend fun getCoursesWithProgress(codes: List<String>): List<CourseWithProgressDto>

    suspend fun getCourseWithProgress(code: String): CourseWithProgressDto?

    suspend fun getCourseWithProgressRemotely(code: String): CourseWithProgressDto?

}