package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairsQueryOptions
import cz.cvut.docta.answer.data.repository.AnswerRepository
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithCorrectAnswers
import cz.cvut.docta.question.mapper.toDomain

class GetQuestionAnswerPairsQuestionWithAnswersUseCaseImpl(
    private val answerRepository: AnswerRepository
) : GetQuestionAnswerPairsQuestionWithAnswersUseCase {
    override suspend fun execute(
        question: QuestionDetails.QuestionAnswerPairs,
        queryOptions: QuestionAnswerPairsQueryOptions
    ): QuestionWithCorrectAnswers.QuestionAnswerPairs? {
        val pairs = answerRepository.getQuestionAnswerPairs(queryOptions = queryOptions)

        val domainQuestion = question.toDomain(pairs = pairs)
            ?: return null

        return QuestionWithCorrectAnswers.QuestionAnswerPairs(
            question = domainQuestion
        )
    }
}