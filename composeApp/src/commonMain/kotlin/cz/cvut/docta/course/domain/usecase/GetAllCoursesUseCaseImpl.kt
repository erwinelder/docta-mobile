package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.domain.model.CourseLightweight
import cz.cvut.docta.course.mapper.toCourseLightweightList

class GetAllCoursesUseCaseImpl(
    private val courseRepository: CourseRepository
) : GetAllCoursesUseCase {
    override suspend fun execute(): List<CourseLightweight> {
        return courseRepository.getAllCourses().toCourseLightweightList()
    }
}