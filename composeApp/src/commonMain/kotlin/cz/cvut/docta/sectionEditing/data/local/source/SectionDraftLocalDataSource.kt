package cz.cvut.docta.sectionEditing.data.local.source

import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity

interface SectionDraftLocalDataSource {

    suspend fun getSectionDraft(id: Int): SectionDraftEntity?

    suspend fun getSectionDrafts(courseCode: String): List<SectionDraftEntity>

    suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity)

}