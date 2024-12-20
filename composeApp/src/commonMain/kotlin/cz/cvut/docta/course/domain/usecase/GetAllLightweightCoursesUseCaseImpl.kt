package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.domain.model.CourseLightweight
import cz.cvut.docta.course.mapper.toCourseLightweightList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetAllLightweightCoursesUseCaseImpl(
    private val courseRepository: CourseRepository
) : GetAllLightweightCoursesUseCase {
    override fun execute(): Flow<List<CourseLightweight>> {
        return courseRepository.getAllCourses().map { it.toCourseLightweightList() }
    }
}