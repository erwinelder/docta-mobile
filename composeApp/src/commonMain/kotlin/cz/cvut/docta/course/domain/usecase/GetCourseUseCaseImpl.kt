package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.domain.model.CourseLightweight

class GetCourseUseCaseImpl(
    private val courseRepository: CourseRepository
) : GetCourseUseCase {
    override suspend fun execute(courseCode: String): CourseLightweight {
        // TODO-USECASE
        return CourseLightweight(
            code = courseCode,
            locale = "cs",
            name = "Course name"
        )
    }
}