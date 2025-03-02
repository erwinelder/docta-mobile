package cz.cvut.docta.courseEditing.domain.usecase

import cz.cvut.docta.courseEditing.domain.model.CourseDraft

interface SaveCourseDraftUseCase {
    suspend fun execute(courseDraft: CourseDraft)
}