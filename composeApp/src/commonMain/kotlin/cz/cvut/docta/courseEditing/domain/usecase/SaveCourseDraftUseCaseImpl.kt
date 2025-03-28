package cz.cvut.docta.courseEditing.domain.usecase

import cz.cvut.docta.courseEditing.data.repository.CourseDraftRepository
import cz.cvut.docta.courseEditing.domain.model.CourseDraft
import cz.cvut.docta.courseEditing.mapper.toDataModel

class SaveCourseDraftUseCaseImpl(
    private val repository: CourseDraftRepository
) : SaveCourseDraftUseCase {
    override suspend fun execute(courseDraft: CourseDraft) {
        repository.saveCourseDraft(courseDraft.toDataModel())
    }
}