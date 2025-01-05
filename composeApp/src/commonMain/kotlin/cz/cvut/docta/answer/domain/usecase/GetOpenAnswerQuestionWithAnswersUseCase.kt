package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.question.data.model.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithAnswers

interface GetOpenAnswerQuestionWithAnswersUseCase {
    suspend fun execute(question: QuestionDetails.OpenAnswer): QuestionWithAnswers.OpenAnswer?
}