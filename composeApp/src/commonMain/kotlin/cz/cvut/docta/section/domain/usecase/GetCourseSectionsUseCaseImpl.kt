package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.domain.model.SectionWithStatistics
import cz.cvut.docta.section.domain.model.SectionStatistics
import cz.cvut.docta.section.mapper.toDomainModels

class GetCourseSectionsUseCaseImpl(
    private val sectionRepository: SectionRepository
) : GetCourseSectionsUseCase {
    override suspend fun execute(courseCode: String): List<SectionWithStatistics> {
        return sectionRepository.getCourseSections(courseCode)
            .toDomainModels()
            .map {
                // TODO-STATISTICS
                SectionWithStatistics(id = it.id, name = it.name, statistics = SectionStatistics())
            }
    }
}