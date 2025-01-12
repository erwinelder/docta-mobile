package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.model.CourseLocale

class GetCourseUseCaseTemp : GetCourseUseCase {
    override suspend fun execute(courseCode: String): Course {
        return Course(
            code = "MAA",
            locale = CourseLocale.Czech,
            name = "Matematická analýza"
        )
    }
}