package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.domain.model.CourseLightweight
import cz.cvut.docta.course.mapper.toCourseLightweight

class GetCourseUseCaseImpl(
    private val courseRepository: CourseRepository
) : GetCourseUseCase {
    override suspend fun execute(courseCode: String): CourseLightweight? {
        return courseRepository.getCourse(courseCode)?.toCourseLightweight()
    }
}