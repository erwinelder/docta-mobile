package cz.cvut.docta.section.data.repository

import cz.cvut.docta.core.data.utils.synchroniseData
import cz.cvut.docta.section.data.remote.model.SectionRemoteEntity
import cz.cvut.docta.section.data.local.source.SectionLocalDataSource
import cz.cvut.docta.section.data.mapper.toSectionEntitiesToSync
import cz.cvut.docta.section.data.local.model.SectionEntity
import cz.cvut.docta.section.data.remote.source.SectionRemoteDataSource

class SectionRepositoryImpl(
    private val localSource: SectionLocalDataSource,
    private val remoteSource: SectionRemoteDataSource
) : SectionRepository {

    private suspend fun synchroniseSections(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getUpdateTime,
            remoteDataGetter = remoteSource::getSectionsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<SectionRemoteEntity>::toSectionEntitiesToSync,
            localSynchroniser = localSource::synchroniseSections
        )
    }

    override suspend fun getSection(courseCode: String, sectionId: Long): SectionEntity? {
        synchroniseSections(courseCode = courseCode)
        return localSource.getSection(sectionId = sectionId)
    }

    override suspend fun getSections(courseCode: String): List<SectionEntity> {
        synchroniseSections(courseCode = courseCode)
        return localSource.getCourseSections(courseCode = courseCode)
    }

}