package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.Course

interface SearchForCourseUseCase {
    suspend fun execute(courseCode: String): Course?
}