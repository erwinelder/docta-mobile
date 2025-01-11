package cz.cvut.docta.section.data.remote.source

import cz.cvut.docta.core.data.model.TableName
import cz.cvut.docta.core.data.remote.AppRemoteDatabase
import cz.cvut.docta.core.data.remote.dao.RemoteUpdateTimeDao
import cz.cvut.docta.core.data.remote.model.RemoteUpdateTime
import cz.cvut.docta.section.data.remote.dao.SectionRemoteDao
import cz.cvut.docta.section.data.remote.model.SectionRemoteEntity

class SectionRemoteDataSourceImpl(
    private val sectionDao: SectionRemoteDao,
    private val updateTimeDao: RemoteUpdateTimeDao
) : SectionRemoteDataSource {

    override suspend fun getUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(tableName = TableName.Section.name, courseCode = courseCode)
    }

    override suspend fun saveUpdateTime(courseCode: String, timestamp: Long) {
        val updateTime = RemoteUpdateTime(
            tableName = TableName.Section.name, courseCode = courseCode, updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun getSectionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<SectionRemoteEntity> {
        return sectionDao.getSectionsAfterTimestamp(courseCode = courseCode, timestamp = timestamp)
    }

}

fun sectionRemoteDataSourceFactory(appRemoteDatabase: AppRemoteDatabase): SectionRemoteDataSource {
    return SectionRemoteDataSourceImpl(
        sectionDao = appRemoteDatabase.sectionRemoteDao(),
        updateTimeDao = appRemoteDatabase.remoteUpdateTimeDao()
    )
}