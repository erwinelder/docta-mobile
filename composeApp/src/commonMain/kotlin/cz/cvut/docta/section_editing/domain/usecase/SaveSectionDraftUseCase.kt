package cz.cvut.docta.section_editing.domain.usecase

import cz.cvut.docta.section_editing.domain.model.SectionDraft

interface SaveSectionDraftUseCase {
    suspend fun execute(sectionDraft: SectionDraft)
}