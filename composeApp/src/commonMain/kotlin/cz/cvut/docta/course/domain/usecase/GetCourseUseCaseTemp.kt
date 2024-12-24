package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.CourseLightweight

class GetCourseUseCaseTemp : GetCourseUseCase {
    override suspend fun execute(courseCode: String): CourseLightweight? {
        return CourseLightweight(
            code = "MAA",
            locale = "cs",
            name = "Matematická analýza"
        )
    }
}