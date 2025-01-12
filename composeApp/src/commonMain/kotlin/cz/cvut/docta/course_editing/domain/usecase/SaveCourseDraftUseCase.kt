package cz.cvut.docta.course_editing.domain.usecase

import cz.cvut.docta.course_editing.domain.model.CourseDraft

interface SaveCourseDraftUseCase {
    suspend fun execute(courseDraft: CourseDraft)
}