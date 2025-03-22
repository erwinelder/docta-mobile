package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.mapper.toDomainModel

class GetCourseUseCaseImpl(
    private val courseRepository: CourseRepository
) : GetCourseUseCase {
    override suspend fun execute(courseCode: String): Course? {
        return courseRepository.getCourse(code = courseCode)?.toDomainModel()
    }
}