package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.mapper.toDomainModels

class GetCoursesUseCaseImpl(
    private val courseRepository: CourseRepository
): GetCoursesUseCase {
    override suspend fun get(codes: List<String>): List<Course> {
        return courseRepository.getCourses(codes = codes).toDomainModels()
    }
}