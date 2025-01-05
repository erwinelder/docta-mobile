package cz.cvut.docta.section_draft.data.repository

import cz.cvut.docta.section_draft.data.local.source.SectionDraftLocalDataSource
import cz.cvut.docta.section_draft.data.model.SectionDraftEntity

class SectionDraftRepositoryImpl(
    private val localSource: SectionDraftLocalDataSource,
) : SectionDraftRepository {

    override suspend fun getSectionEditing(id: Long): SectionDraftEntity? {
        return localSource.getSectionDraft(id)
    }

    override suspend fun saveSectionEditing(sectionDraftEntity: SectionDraftEntity) {
        localSource.saveSectionDraft(sectionDraftEntity)
    }
}