package cz.cvut.docta.course_editing.domain.usecase

import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course_editing.data.repository.CourseDraftRepository
import cz.cvut.docta.course_editing.domain.model.CourseDraft
import cz.cvut.docta.course_editing.mapper.toCourseDraft
import cz.cvut.docta.course_editing.mapper.toDomain

class GetCourseDraftUseCaseImpl(
    private val courseRepository: CourseRepository,
    private val courseDraftRepository: CourseDraftRepository
) : GetCourseDraftUseCase {
    override suspend fun execute(courseCode: String): CourseDraft? {
        return courseDraftRepository.getCourseEditing(courseCode)?.toDomain()
            ?: courseRepository.getCourse(courseCode = courseCode)?.toCourseDraft()
    }
}