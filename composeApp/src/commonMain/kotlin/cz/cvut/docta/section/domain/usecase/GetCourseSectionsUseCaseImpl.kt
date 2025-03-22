package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.domain.model.Section
import cz.cvut.docta.section.mapper.toDomainModels

class GetCourseSectionsUseCaseImpl(
    private val sectionRepository: SectionRepository
) : GetCourseSectionsUseCase {
    override suspend fun execute(courseCode: String): List<Section> {
        return sectionRepository.getSections(courseCode = courseCode)
            .sortedBy { it.orderNum }
            .toDomainModels()
    }
}