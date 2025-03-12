package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.domain.model.SectionProgressState

class GetSectionsWithUserStatsUseCaseImpl (
    private val sectionRepository: SectionRepository
) : GetSectionsWithUserStatsUseCase {
    override suspend fun execute(courseCode: String): List<SectionProgressState> {
        return sectionRepository.getSectionsWithUserStats(courseCode = courseCode)
    }
}