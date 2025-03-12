package cz.cvut.docta.section.data.repository

import cz.cvut.docta.section.data.local.model.SectionEntity
import cz.cvut.docta.section.domain.model.SectionProgressState

interface SectionRepository {

    suspend fun getSection(courseCode: String, sectionId: Long): SectionEntity?

    suspend fun getSections(courseCode: String): List<SectionEntity>

    suspend fun getSectionsWithUserStats(courseCode: String): List<SectionProgressState>

}