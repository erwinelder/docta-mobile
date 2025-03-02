package cz.cvut.docta.sectionEditing.data.repository

import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity

interface SectionDraftRepository {

    suspend fun getSectionEditing(id: Long): SectionDraftEntity?

    suspend fun saveSectionEditing(sectionDraftEntity: SectionDraftEntity)
}