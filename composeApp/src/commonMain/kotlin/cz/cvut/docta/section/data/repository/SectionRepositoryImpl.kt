package cz.cvut.docta.section.data.repository

import cz.cvut.docta.section.data.local.source.SectionLocalDataSource
import cz.cvut.docta.section.data.model.SectionEntity
import kotlinx.coroutines.flow.Flow

class SectionRepositoryImpl(
    private val localSource: SectionLocalDataSource
) : SectionRepository {

    override suspend fun getSection(sectionId: Long): SectionEntity {
        return localSource.getSection(sectionId = sectionId)
    }

    override suspend fun getCourseSections(courseCode: String): List<SectionEntity> {
        return localSource.getCourseSections(courseCode = courseCode)
    }

}