package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.answer.data.model.QuestionAnswerPairsQueryOptions
import cz.cvut.docta.answer.data.repository.AnswerRepository
import cz.cvut.docta.question.data.model.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithAnswers
import cz.cvut.docta.question.mapper.toDomain

class GetQuestionAnswerPairsQuestionWithAnswersUseCaseImpl(
    private val answerRepository: AnswerRepository
) : GetQuestionAnswerPairsQuestionWithAnswersUseCase {
    override suspend fun execute(
        question: QuestionDetails.QuestionAnswerPairs,
        queryOptions: QuestionAnswerPairsQueryOptions
    ): QuestionWithAnswers.QuestionAnswerPairs? {
        val pairs = answerRepository.getQuestionAnswerPairs(queryOptions = queryOptions)

        val domainQuestion = question.toDomain(pairs = pairs)
            ?: return null

        return QuestionWithAnswers.QuestionAnswerPairs(
            question = domainQuestion
        )
    }
}