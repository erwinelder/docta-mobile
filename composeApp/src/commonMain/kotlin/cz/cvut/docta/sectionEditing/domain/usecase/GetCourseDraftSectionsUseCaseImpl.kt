package cz.cvut.docta.sectionEditing.domain.usecase

import cz.cvut.docta.sectionEditing.data.repository.SectionDraftRepository
import cz.cvut.docta.sectionEditing.domain.model.SectionDraft
import cz.cvut.docta.sectionEditing.mapper.toSectionDrafts

class GetCourseDraftSectionsUseCaseImpl(
    private val sectionDraftRepository: SectionDraftRepository
) : GetCourseDraftSectionsUseCase {

    override suspend fun execute(courseCode: String): List<SectionDraft> {
        return sectionDraftRepository.getCourseSectionsDrafts(courseCode).toSectionDrafts()
    }
}