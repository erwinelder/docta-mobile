package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.lesson.domain.model.Lesson

interface GetSectionLessonsUseCase {
    suspend fun execute(sectionId: Long): List<Lesson>
}