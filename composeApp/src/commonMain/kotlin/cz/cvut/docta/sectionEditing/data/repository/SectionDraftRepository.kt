package cz.cvut.docta.sectionEditing.data.repository

import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity

interface SectionDraftRepository {

    suspend fun getSectionDraft(id: Int): SectionDraftEntity?

    suspend fun getSectionDrafts(courseCode: String): List<SectionDraftEntity>

    suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity)

}