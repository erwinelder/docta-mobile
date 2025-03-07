package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.domain.model.Section
import cz.cvut.docta.section.mapper.toDomainModels

class GetCourseDraftSectionsUseCaseImpl (
    /* TODO-COURSE-MANAGEMENT: Use section draft repository instead of section repository */
    private val sectionRepository: SectionRepository
) : GetCourseDraftSectionsUseCase {
    override suspend fun execute(courseCode: String): List<Section> {
        return sectionRepository.getCourseSections(courseCode).toDomainModels()
    }
}