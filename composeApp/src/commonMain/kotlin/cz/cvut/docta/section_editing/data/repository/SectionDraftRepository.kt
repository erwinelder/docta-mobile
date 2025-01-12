package cz.cvut.docta.section_editing.data.repository

import cz.cvut.docta.section_editing.data.model.SectionDraftEntity

interface SectionDraftRepository {

    suspend fun getSectionEditing(id: Long): SectionDraftEntity?

    suspend fun saveSectionEditing(sectionDraftEntity: SectionDraftEntity)
}