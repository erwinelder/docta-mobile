package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.lesson.domain.model.LessonDraft

interface GetSectionLessonsDraftsUseCase {
    suspend fun execute(sectionId: Int): List<LessonDraft>
}