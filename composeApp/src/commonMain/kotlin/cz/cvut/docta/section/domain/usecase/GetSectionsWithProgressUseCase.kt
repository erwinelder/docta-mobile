package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.domain.model.SectionWithProgress

interface GetSectionsWithProgressUseCase {
    suspend fun execute(courseCode: String): List<SectionWithProgress>
}