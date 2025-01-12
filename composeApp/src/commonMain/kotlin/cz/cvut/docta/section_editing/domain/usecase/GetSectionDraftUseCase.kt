package cz.cvut.docta.section_editing.domain.usecase

import cz.cvut.docta.section_editing.domain.model.SectionDraft

interface GetSectionDraftUseCase {
    suspend fun execute(id: Long): SectionDraft?
}