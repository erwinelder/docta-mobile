package cz.cvut.docta.section.data.local.source

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.section.data.local.dao.SectionDao
import cz.cvut.docta.section.data.model.SectionEntity

class SectionLocalDataSourceImpl(
    private val dao: SectionDao
) : SectionLocalDataSource {

    override suspend fun getSection(sectionId: Long): SectionEntity {
        return dao.getSection(id = sectionId)
    }

    override suspend fun getCourseSections(courseCode: String): List<SectionEntity> {
        return dao.getCourseSections(courseCode = courseCode)
    }

}

fun sectionLocalDataSourceFactory(
    appLocalDatabase: AppLocalDatabase
): SectionLocalDataSourceImpl {
    return SectionLocalDataSourceImpl(dao = appLocalDatabase.sectionDao())
}