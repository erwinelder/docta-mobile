package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithAnswers

interface GetFillInBlanksQuestionWithAnswersUseCase {
    suspend fun execute(question: QuestionDetails.FillInBlanks): QuestionWithAnswers.FillInBlanks?
}