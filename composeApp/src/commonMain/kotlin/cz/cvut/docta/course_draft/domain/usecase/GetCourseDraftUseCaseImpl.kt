package cz.cvut.docta.course_draft.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course_draft.data.repository.CourseDraftRepository
import cz.cvut.docta.course_draft.domain.model.CourseDraft
import cz.cvut.docta.course_draft.mapper.toCourseDraft
import cz.cvut.docta.course_draft.mapper.toDomain

class GetCourseDraftUseCaseImpl(
    private val courseRepository: CourseRepository,
    private val courseDraftRepository: CourseDraftRepository
) : GetCourseDraftUseCase {
    override suspend fun execute(courseCode: String): CourseDraft? {
        return courseDraftRepository.getCourseEditing(courseCode)?.toDomain()
            ?: courseRepository.getCourse(courseCode = courseCode)?.toCourseDraft()
    }
}