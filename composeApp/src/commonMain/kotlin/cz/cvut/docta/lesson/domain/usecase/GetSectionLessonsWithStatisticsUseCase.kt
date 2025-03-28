package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.lesson.domain.model.LessonWithProgress

interface GetSectionLessonsWithStatisticsUseCase {
    suspend fun execute(sectionId: Int): List<LessonWithProgress>
}