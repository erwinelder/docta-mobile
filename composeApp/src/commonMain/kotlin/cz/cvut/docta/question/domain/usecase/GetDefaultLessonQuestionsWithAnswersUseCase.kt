package cz.cvut.docta.question.domain.usecase

import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.domain.model.QuestionWithCorrectAnswers

interface GetDefaultLessonQuestionsWithAnswersUseCase {
    suspend fun execute(
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<QuestionWithCorrectAnswers>
}