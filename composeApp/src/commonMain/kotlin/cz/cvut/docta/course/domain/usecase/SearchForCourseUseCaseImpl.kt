package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.mapper.toDomainModel

class SearchForCourseUseCaseImpl(
    private val courseRepository: CourseRepository
) : SearchForCourseUseCase {
    override suspend fun execute(courseCode: String): Course? {
        return courseRepository.getCourseRemotely(courseCode = courseCode)?.toDomainModel()
    }
}