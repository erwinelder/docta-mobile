package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.question.data.model.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithAnswers

interface GetAnswerOptionsQuestionWithAnswerUseCase {
    suspend fun execute(question: QuestionDetails.AnswerOptions): QuestionWithAnswers.AnswerOptions?
}