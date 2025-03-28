package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.domain.model.CourseWithProgress
import cz.cvut.docta.course.mapper.toDomainModel

class GetCoursesWithProgressUseCaseImpl(
    private val courseRepository: CourseRepository
) : GetCoursesWithProgressUseCase {
    override suspend fun execute(codes: List<String>): List<CourseWithProgress> {
        return courseRepository.getCoursesWithProgress(codes = codes).map { it.toDomainModel() }
    }
}