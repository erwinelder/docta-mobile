package cz.cvut.docta.section_draft.domain.usecase

import cz.cvut.docta.section_draft.domain.model.SectionDraft

interface SaveSectionDraftUseCase {
    suspend fun execute(sectionDraft: SectionDraft)
}