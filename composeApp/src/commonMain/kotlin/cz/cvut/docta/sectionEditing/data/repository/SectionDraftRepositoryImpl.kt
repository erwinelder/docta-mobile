package cz.cvut.docta.sectionEditing.data.repository

import cz.cvut.docta.sectionEditing.data.local.source.SectionDraftLocalDataSource
import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity

class SectionDraftRepositoryImpl(
    private val localSource: SectionDraftLocalDataSource,
) : SectionDraftRepository {

    override suspend fun getSectionDraft(id: Int): SectionDraftEntity? {
        return localSource.getSectionDraft(id = id)
    }

    override suspend fun getSectionDrafts(courseCode: String): List<SectionDraftEntity> {
        return localSource.getSectionDrafts(courseCode = courseCode)
    }

    override suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity) {
        localSource.saveSectionDraft(sectionDraftEntity)
    }

}