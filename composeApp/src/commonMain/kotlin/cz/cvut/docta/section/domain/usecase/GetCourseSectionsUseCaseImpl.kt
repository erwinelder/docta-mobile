package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.domain.model.Section

class GetCourseSectionsUseCaseImpl(
    private val sectionRepository: SectionRepository
) : GetCourseSectionsUseCase {
    override suspend fun execute(courseCode: String): List<Section> {
        // TODO-USECASE
        return emptyList()
    }
}