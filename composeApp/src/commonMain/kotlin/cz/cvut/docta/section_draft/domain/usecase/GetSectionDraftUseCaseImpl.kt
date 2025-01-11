package cz.cvut.docta.section_draft.domain.usecase

import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section_draft.data.repository.SectionDraftRepository
import cz.cvut.docta.section_draft.domain.model.SectionDraft
import cz.cvut.docta.section_draft.mapper.toDomain
import cz.cvut.docta.section_draft.mapper.toSectionDraft

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