package cz.cvut.docta.section_editing.domain.usecase

import cz.cvut.docta.section_editing.data.repository.SectionDraftRepository
import cz.cvut.docta.section_editing.domain.model.SectionDraft
import cz.cvut.docta.section_editing.mapper.toEntity

class SaveSectionDraftUseCaseImpl(
    private val repository: SectionDraftRepository
) : SaveSectionDraftUseCase {

    override suspend fun execute(sectionDraft: SectionDraft) {
        repository.saveSectionEditing(sectionDraft.toEntity())
    }
}