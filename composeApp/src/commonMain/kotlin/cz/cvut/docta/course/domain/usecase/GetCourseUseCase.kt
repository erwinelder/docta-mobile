package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.CourseLightweight

interface GetCourseUseCase {
    suspend fun execute(courseCode: String): CourseLightweight?
}