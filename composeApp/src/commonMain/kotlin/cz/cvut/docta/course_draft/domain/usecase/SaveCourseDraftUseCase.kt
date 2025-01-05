package cz.cvut.docta.course_draft.domain.usecase

import cz.cvut.docta.course_draft.domain.model.CourseDraft

interface SaveCourseDraftUseCase {
    suspend fun execute(courseDraft: CourseDraft)
}