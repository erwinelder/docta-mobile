package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions

interface GetQuestionsQueryOptionsUseCase {
    suspend fun execute(lessonId: Long): LessonQuestionsQueryOptions?
}