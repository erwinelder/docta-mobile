package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.domain.model.Section
import cz.cvut.docta.section.mapper.toSectionLightweight

class GetSectionUseCaseImpl(
    private val sectionRepository: SectionRepository,
    private val courseContext: CourseContext
) : GetSectionUseCase {
    override suspend fun execute(sectionId: Long): Section? {
        return sectionRepository
            .getSection(courseCode = courseContext.getCourseCode(), sectionId = sectionId)
            ?.toSectionLightweight()
    }
}