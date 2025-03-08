package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.Course

interface GetCoursesUseCase {
    suspend fun execute(codes: List<String>): List<Course>
}