package cz.cvut.docta.sectionEditing.data.repository

import cz.cvut.docta.sectionEditing.data.local.source.SectionDraftLocalDataSource
import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity

class SectionDraftRepositoryImpl(
    private val localSource: SectionDraftLocalDataSource,
) : SectionDraftRepository {

    override suspend fun getSectionDraft(id: Long): SectionDraftEntity? {
        return localSource.getSectionDraft(id = id)
    }

    override suspend fun getCourseSectionsDrafts(courseCode: String): List<SectionDraftEntity> {
        // TODO-COURSE-MANAGEMENT: Implement this method
        return emptyList()
    }

    override suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity) {
        localSource.saveSectionDraft(sectionDraftEntity)
    }

}