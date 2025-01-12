package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.mapper.toDomainModels

class GetAllCoursesUseCaseImpl(
    private val courseRepository: CourseRepository
) : GetAllCoursesUseCase {
    override suspend fun execute(): List<Course> {
        return courseRepository.getAllCourses().toDomainModels()
    }
}