package cz.cvut.docta.sectionEditing.domain.usecase

import cz.cvut.docta.sectionEditing.domain.model.SectionDraft

interface GetSectionDraftUseCase {
    suspend fun execute(id: Int): SectionDraft?
}