package cz.cvut.docta.section_draft.domain.usecase

import cz.cvut.docta.section_draft.data.repository.SectionDraftRepository
import cz.cvut.docta.section_draft.domain.model.SectionDraft
import cz.cvut.docta.section_draft.mapper.toEntity

class SaveSectionDraftUseCaseImpl(
    private val repository: SectionDraftRepository
) : SaveSectionDraftUseCase {

    override suspend fun execute(sectionDraft: SectionDraft) {
        repository.saveSectionEditing(sectionDraft.toEntity())
    }
}