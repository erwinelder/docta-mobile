package cz.cvut.docta.courseEditing.domain.usecase

import cz.cvut.docta.courseEditing.domain.model.CourseDraft

interface SaveRemoteCourseDraftUseCase {
    suspend fun execute(courseDraft: CourseDraft)
}