package cz.cvut.docta.section.data.local.source

import cz.cvut.docta.core.data.database.AppLocalDatabase
import cz.cvut.docta.core.data.local.dao.LocalUpdateTimeDao
import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.core.data.model.TableName
import cz.cvut.docta.section.data.local.dao.SectionDao
import cz.cvut.docta.section.data.local.model.SectionEntity

class SectionLocalDataSourceImpl(
    private val sectionDao: SectionDao,
    private val updateTimeDao: LocalUpdateTimeDao
) : SectionLocalDataSource {

    override suspend fun getUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.Section.name, courseCode = courseCode
        )
    }

    override suspend fun saveUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.Section.name, updateTime = timestamp, courseCode = courseCode
        )
    }

    override suspend fun synchroniseSections(
        sectionsToSync: EntitiesToSynchronise<SectionEntity>,
        courseCode: String,
        timestamp: Long
    ) {
        sectionDao.deleteAndUpsertSections(
            toDelete = sectionsToSync.toDelete, toUpsert = sectionsToSync.toUpsert
        )
        saveUpdateTime(courseCode = courseCode, timestamp = timestamp)
    }

    override suspend fun getSection(sectionId: Long): SectionEntity? {
        return sectionDao.getSection(id = sectionId)
    }

    override suspend fun getCourseSections(courseCode: String): List<SectionEntity> {
        return sectionDao.getCourseSections(courseCode = courseCode)
    }

}

fun sectionLocalDataSourceFactory(appLocalDatabase: AppLocalDatabase): SectionLocalDataSourceImpl {
    return SectionLocalDataSourceImpl(
        sectionDao = appLocalDatabase.sectionDao(),
        updateTimeDao = appLocalDatabase.localUpdateTimeDao()
    )
}