package cz.cvut.docta.courseEditing.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.courseEditing.data.repository.CourseDraftRepository
import cz.cvut.docta.courseEditing.domain.model.CourseDraft
import cz.cvut.docta.courseEditing.mapper.toCourseDraft
import cz.cvut.docta.courseEditing.mapper.toDomainModel

class GetCourseDraftUseCaseImpl(
    private val courseRepository: CourseRepository,
    private val courseDraftRepository: CourseDraftRepository
) : GetCourseDraftUseCase {
    override suspend fun execute(courseCode: String): CourseDraft? {
        return courseDraftRepository.getCourseDraft(courseCode)?.toDomainModel()
            ?: courseRepository.getCourse(code = courseCode)?.toCourseDraft()
    }
}