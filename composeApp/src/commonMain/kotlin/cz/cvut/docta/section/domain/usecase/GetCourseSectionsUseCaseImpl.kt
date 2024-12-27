package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.domain.model.Section
import cz.cvut.docta.section.domain.model.SectionStatistics
import cz.cvut.docta.section.mapper.toSectionsLightweight

class GetCourseSectionsUseCaseImpl(
    private val sectionRepository: SectionRepository
) : GetCourseSectionsUseCase {
    override suspend fun execute(courseCode: String): List<Section> {
        return sectionRepository.getCourseSections(courseCode)
            .toSectionsLightweight()
            .map {
                // TODO-STATISTICS
                Section(id = it.id, name = it.name, statistics = SectionStatistics())
            }
    }
}