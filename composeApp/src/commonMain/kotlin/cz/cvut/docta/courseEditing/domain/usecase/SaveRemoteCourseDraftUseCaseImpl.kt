package cz.cvut.docta.courseEditing.domain.usecase

import cz.cvut.docta.courseEditing.data.repository.CourseDraftRemoteRepository
import cz.cvut.docta.courseEditing.domain.model.CourseDraft
import cz.cvut.docta.courseEditing.mapper.toEntity

class SaveRemoteCourseDraftUseCaseImpl (
    private val repository: CourseDraftRemoteRepository
) : SaveRemoteCourseDraftUseCase {
    override suspend fun execute(courseDraft: CourseDraft) {
        repository.saveCourseEditing(courseDraft.toEntity())
    }
}