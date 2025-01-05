package cz.cvut.docta.course_draft.domain.usecase

import cz.cvut.docta.course_draft.data.repository.CourseDraftRepository
import cz.cvut.docta.course_draft.domain.model.CourseDraft
import cz.cvut.docta.course_draft.mapper.toEntity

class SaveCourseDraftUseCaseImpl(
    private val repository: CourseDraftRepository
) : SaveCourseDraftUseCase {
    override suspend fun execute(courseDraft: CourseDraft) {
        repository.saveCourseEditing(courseDraft.toEntity())
    }
}