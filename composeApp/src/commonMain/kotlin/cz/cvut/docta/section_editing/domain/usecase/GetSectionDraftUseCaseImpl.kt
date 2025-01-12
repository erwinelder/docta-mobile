package cz.cvut.docta.section_editing.domain.usecase

import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section_editing.data.repository.SectionDraftRepository
import cz.cvut.docta.section_editing.domain.model.SectionDraft
import cz.cvut.docta.section_editing.mapper.toDomain
import cz.cvut.docta.section_editing.mapper.toSectionDraft

class GetSectionDraftUseCaseImpl(
    private val sectionRepository: SectionRepository,
    private val sectionDraftRepository: SectionDraftRepository,
    private val courseContext: CourseContext
) : GetSectionDraftUseCase {

    override suspend fun execute(id: Long): SectionDraft? {
        return sectionDraftRepository.getSectionEditing(id)?.toDomain()
            ?: sectionRepository
                .getSection(courseCode = courseContext.getCourseCode(), sectionId = id)
                ?.toSectionDraft()
    }
}