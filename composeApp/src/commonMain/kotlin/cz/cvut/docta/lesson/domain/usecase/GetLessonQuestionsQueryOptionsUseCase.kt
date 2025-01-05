package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.question.data.model.LessonQuestionsQueryOptions

interface GetLessonQuestionsQueryOptionsUseCase {
    suspend fun execute(lessonId: Long): LessonQuestionsQueryOptions?
}