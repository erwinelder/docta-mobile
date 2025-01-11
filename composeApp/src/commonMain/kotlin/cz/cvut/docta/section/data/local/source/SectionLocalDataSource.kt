package cz.cvut.docta.section.data.local.source

import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.section.data.local.model.SectionEntity

interface SectionLocalDataSource {

    suspend fun getUpdateTime(courseCode: String): Long?

    suspend fun saveUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseSections(
        sectionsToSync: EntitiesToSynchronise<SectionEntity>,
        courseCode: String,
        timestamp: Long
    )

    suspend fun getSection(sectionId: Long): SectionEntity?

    suspend fun getCourseSections(courseCode: String): List<SectionEntity>

}