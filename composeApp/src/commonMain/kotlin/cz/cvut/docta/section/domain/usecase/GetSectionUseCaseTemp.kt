package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.domain.model.SectionLightweight

class GetSectionUseCaseTemp : GetSectionUseCase {
    override suspend fun execute(sectionId: Long): SectionLightweight? {
        return SectionLightweight(
            id = 1,
            name = "Limita funkce, spojitost"
        )
    }
}