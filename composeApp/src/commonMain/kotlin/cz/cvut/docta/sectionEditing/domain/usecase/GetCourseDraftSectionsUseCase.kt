package cz.cvut.docta.sectionEditing.domain.usecase

import cz.cvut.docta.sectionEditing.domain.model.SectionDraft

interface GetCourseDraftSectionsUseCase {
    suspend fun execute(courseCode: String): List<SectionDraft>
}