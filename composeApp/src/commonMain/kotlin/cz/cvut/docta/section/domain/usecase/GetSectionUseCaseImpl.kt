package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.domain.model.SectionLightweight
import cz.cvut.docta.section.mapper.toSectionLightweight

class GetSectionUseCaseImpl(
    private val sectionRepository: SectionRepository
) : GetSectionUseCase {
    override suspend fun execute(sectionId: Long): SectionLightweight {
        return sectionRepository.getSection(sectionId = sectionId).toSectionLightweight()
    }
}