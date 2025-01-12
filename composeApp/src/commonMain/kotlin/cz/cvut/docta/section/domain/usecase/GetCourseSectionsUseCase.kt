package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.domain.model.SectionWithStatistics

interface GetCourseSectionsUseCase {
    suspend fun execute(courseCode: String): List<SectionWithStatistics>
}