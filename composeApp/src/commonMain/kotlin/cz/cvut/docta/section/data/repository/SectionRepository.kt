package cz.cvut.docta.section.data.repository

import cz.cvut.docta.section.data.model.SectionDto
import cz.cvut.docta.section.data.model.SectionWithProgressDto

interface SectionRepository {

    suspend fun getSections(courseCode: String): List<SectionDto>

    suspend fun getSection(courseCode: String, sectionId: Int): SectionDto?

    suspend fun getSectionsWithProgress(courseCode: String): List<SectionWithProgressDto>

}