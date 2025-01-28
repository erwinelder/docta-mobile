package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithCorrectAnswers

interface GetFillInBlanksQuestionWithAnswersUseCase {
    suspend fun execute(question: QuestionDetails.FillInBlanks): QuestionWithCorrectAnswers.FillInBlanks?
}