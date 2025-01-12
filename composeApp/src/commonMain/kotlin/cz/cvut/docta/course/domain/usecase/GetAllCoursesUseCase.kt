package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.Course

interface GetAllCoursesUseCase {
    suspend fun execute(): List<Course>
}