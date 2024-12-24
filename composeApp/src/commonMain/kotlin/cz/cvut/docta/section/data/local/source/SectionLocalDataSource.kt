package cz.cvut.docta.section.data.local.source

import cz.cvut.docta.section.data.model.SectionEntity

interface SectionLocalDataSource {

    suspend fun getSection(sectionId: Long): SectionEntity

    suspend fun getCourseSections(courseCode: String): List<SectionEntity>

}