package cz.cvut.docta.section.data.repository

import cz.cvut.docta.section.data.local.model.SectionEntity

interface SectionRepository {

    suspend fun getSection(courseCode: String, sectionId: Long): SectionEntity?

    suspend fun getCourseSections(courseCode: String): List<SectionEntity>

}