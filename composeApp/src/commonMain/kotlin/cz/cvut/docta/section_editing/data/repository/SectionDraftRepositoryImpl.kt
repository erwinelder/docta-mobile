package cz.cvut.docta.section_editing.data.repository

import cz.cvut.docta.section_editing.data.local.source.SectionDraftLocalDataSource
import cz.cvut.docta.section_editing.data.model.SectionDraftEntity

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