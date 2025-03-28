package cz.cvut.docta.sectionEditing.data.local.source

import cz.cvut.docta.core.data.database.AppDatabase
import cz.cvut.docta.sectionEditing.data.local.dao.SectionDraftDao
import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity

class SectionDraftLocalDataSourceImpl(
    private val dao: SectionDraftDao
) : SectionDraftLocalDataSource {

    override suspend fun getSectionDraft(id: Int): SectionDraftEntity? {
        return dao.getSectionDraft(id = id)
    }

    override suspend fun getSectionDrafts(courseCode: String): List<SectionDraftEntity> {
        return dao.getSectionDrafts(courseCode = courseCode)
    }

    override suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity) {
        dao.saveSectionDraft(sectionDraftEntity = sectionDraftEntity)
    }

}

fun sectionDraftLocalDataSourceFactory(appDatabase: AppDatabase): SectionDraftLocalDataSource {
    return SectionDraftLocalDataSourceImpl(
        dao = appDatabase.sectionEditingDao()
    )
}