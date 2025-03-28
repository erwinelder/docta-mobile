package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.domain.model.Section

interface GetSectionUseCase {
    suspend fun execute(sectionId: Int): Section?
}