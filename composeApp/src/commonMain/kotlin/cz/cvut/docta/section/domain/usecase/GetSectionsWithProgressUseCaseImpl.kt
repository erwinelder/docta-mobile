package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.domain.model.SectionWithProgress
import cz.cvut.docta.section.mapper.toDomainModel

class GetSectionsWithProgressUseCaseImpl(
    private val sectionRepository: SectionRepository
) : GetSectionsWithProgressUseCase {
    override suspend fun execute(courseCode: String): List<SectionWithProgress> {
        return sectionRepository.getSectionsWithProgress(courseCode = courseCode).map {
            it.toDomainModel()
        }
    }
}