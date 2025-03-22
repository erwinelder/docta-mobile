package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.CourseWithProgress

interface SearchForCourseUseCase {
    suspend fun execute(courseCode: String): CourseWithProgress?
}