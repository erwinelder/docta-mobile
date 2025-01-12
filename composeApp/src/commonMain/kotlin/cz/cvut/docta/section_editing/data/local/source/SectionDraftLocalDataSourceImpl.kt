package cz.cvut.docta.section_editing.data.local.source

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.section_editing.data.local.dao.SectionDraftDao
import cz.cvut.docta.section_editing.data.model.SectionDraftEntity

class SectionDraftLocalDataSourceImpl(
    private val dao: SectionDraftDao
) : SectionDraftLocalDataSource {

    override suspend fun getSectionDraft(id: Long): SectionDraftEntity? {
        return dao.getSectionDraft(id)
    }

    override suspend fun saveSectionDraft(sectionDraftEntity: SectionDraftEntity) {
        dao.saveSectionDraft(sectionDraftEntity)
    }

}

fun sectionDraftLocalDataSourceFactory(appLocalDatabase: AppLocalDatabase): SectionDraftLocalDataSource {
    return SectionDraftLocalDataSourceImpl(
        dao = appLocalDatabase.sectionEditingDao()
    )
}