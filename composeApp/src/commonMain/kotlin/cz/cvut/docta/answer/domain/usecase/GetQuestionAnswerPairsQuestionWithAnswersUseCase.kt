package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairsQueryOptions
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithCorrectAnswers

interface GetQuestionAnswerPairsQuestionWithAnswersUseCase {
    suspend fun execute(
        question: QuestionDetails.QuestionAnswerPairs,
        queryOptions: QuestionAnswerPairsQueryOptions
    ): QuestionWithCorrectAnswers.QuestionAnswerPairs?
}