package cz.cvut.docta.course.data.repository

import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.course.domain.model.CourseLightweight

interface CourseRepository {

    suspend fun getAllCourses(): List<CourseLightweight>
    suspend fun getCourse(courseCode: String): CourseEntity?

}