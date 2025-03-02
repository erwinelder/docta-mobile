package cz.cvut.docta.sectionEditing.data.local.source

import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity

interface SectionDraftLocalDataSource {

    suspend fun getSectionDraft(id: Long): SectionDraftEntity?

    suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity)

}