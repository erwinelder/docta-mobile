package cz.cvut.docta.section_draft.data.local.source

import cz.cvut.docta.section_draft.data.model.SectionDraftEntity

interface SectionDraftLocalDataSource {

    suspend fun getSectionDraft(id: Long): SectionDraftEntity?

    suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity)

}