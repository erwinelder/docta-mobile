package cz.cvut.docta.sectionEditing.domain.usecase

import cz.cvut.docta.sectionEditing.domain.model.SectionDraft

interface SaveSectionDraftUseCase {
    suspend fun execute(sectionDraft: SectionDraft)
}