package cz.cvut.docta.sectionEditing.data.repository

import cz.cvut.docta.sectionEditing.data.local.source.SectionDraftLocalDataSource
import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity

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