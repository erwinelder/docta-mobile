package cz.cvut.docta.section_draft.data.repository

import cz.cvut.docta.section_draft.data.model.SectionDraftEntity

interface SectionDraftRepository {

    suspend fun getSectionEditing(id: Long): SectionDraftEntity?

    suspend fun saveSectionEditing(sectionDraftEntity: SectionDraftEntity)
}