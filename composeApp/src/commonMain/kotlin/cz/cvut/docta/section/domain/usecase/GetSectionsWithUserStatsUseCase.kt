package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.domain.model.SectionProgressState
import cz.cvut.docta.section.domain.model.SectionWithStatistics

interface GetSectionsWithUserStatsUseCase {
    suspend fun execute(courseCode: String): List<SectionProgressState>
}