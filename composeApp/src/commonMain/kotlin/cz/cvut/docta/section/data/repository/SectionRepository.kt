package cz.cvut.docta.section.data.repository

import cz.cvut.docta.section.data.model.SectionEntity

interface SectionRepository {

    suspend fun getSection(sectionId: Long): SectionEntity?

    suspend fun getCourseSections(courseCode: String): List<SectionEntity>

}