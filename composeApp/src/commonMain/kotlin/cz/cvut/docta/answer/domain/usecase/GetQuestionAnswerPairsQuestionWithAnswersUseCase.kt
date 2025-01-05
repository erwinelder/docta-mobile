package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.answer.data.model.QuestionAnswerPairsQueryOptions
import cz.cvut.docta.question.data.model.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithAnswers

interface GetQuestionAnswerPairsQuestionWithAnswersUseCase {
    suspend fun execute(
        question: QuestionDetails.QuestionAnswerPairs,
        queryOptions: QuestionAnswerPairsQueryOptions
    ): QuestionWithAnswers.QuestionAnswerPairs?
}