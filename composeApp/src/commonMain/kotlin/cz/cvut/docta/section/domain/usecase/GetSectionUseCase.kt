package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.domain.model.SectionLightweight

interface GetSectionUseCase {
    suspend fun execute(sectionId: Long): SectionLightweight?
}