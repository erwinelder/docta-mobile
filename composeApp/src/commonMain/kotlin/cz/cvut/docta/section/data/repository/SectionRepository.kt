package cz.cvut.docta.section.data.repository

import cz.cvut.docta.section.data.model.SectionDto

interface SectionRepository {

    suspend fun getSection(courseCode: String, sectionId: Long): SectionDto?

    suspend fun getSections(courseCode: String): List<SectionDto>

}