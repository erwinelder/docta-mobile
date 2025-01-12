package cz.cvut.docta.section_editing.data.local.source

import cz.cvut.docta.section_editing.data.model.SectionDraftEntity

interface SectionDraftLocalDataSource {

    suspend fun getSectionDraft(id: Long): SectionDraftEntity?

    suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity)

}