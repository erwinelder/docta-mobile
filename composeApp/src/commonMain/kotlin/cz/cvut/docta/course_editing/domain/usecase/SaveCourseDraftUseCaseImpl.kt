package cz.cvut.docta.course_editing.domain.usecase

import cz.cvut.docta.course_editing.data.repository.CourseDraftRepository
import cz.cvut.docta.course_editing.domain.model.CourseDraft
import cz.cvut.docta.course_editing.mapper.toEntity

class SaveCourseDraftUseCaseImpl(
    private val repository: CourseDraftRepository
) : SaveCourseDraftUseCase {
    override suspend fun execute(courseDraft: CourseDraft) {
        repository.saveCourseEditing(courseDraft.toEntity())
    }
}