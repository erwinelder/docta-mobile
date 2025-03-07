package cz.cvut.docta.sectionEditing.domain.usecase

import cz.cvut.docta.sectionEditing.data.repository.SectionDraftRepository
import cz.cvut.docta.sectionEditing.domain.model.SectionDraft
import cz.cvut.docta.sectionEditing.mapper.toEntity

class SaveSectionDraftUseCaseImpl(
    private val repository: SectionDraftRepository
) : SaveSectionDraftUseCase {

    override suspend fun execute(sectionDraft: SectionDraft) {
        repository.saveSectionDraft(sectionDraft.toEntity())
    }
}