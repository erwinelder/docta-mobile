package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.domain.model.Section

class GetSectionUseCaseTemp : GetSectionUseCase {
    override suspend fun execute(sectionId: Long): Section? {
        return Section(
            id = 1,
            name = "Limita funkce, spojitost"
        )
    }
}