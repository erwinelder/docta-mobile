package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.answer.data.repository.AnswerRepository
import cz.cvut.docta.answer.mapper.toDomainOption
import cz.cvut.docta.question.data.model.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithAnswers
import cz.cvut.docta.question.mapper.toDomain

class GetAnswerOptionsQuestionWithAnswerUseCaseImpl(
    private val answerRepository: AnswerRepository
) : GetAnswerOptionsQuestionWithAnswerUseCase {
    override suspend fun execute(
        question: QuestionDetails.AnswerOptions
    ): QuestionWithAnswers.AnswerOptions? {
        val options = answerRepository.getAnswerOptions(question.id)
        val domainQuestion = question.toDomain(options = options) ?: return null
        return QuestionWithAnswers.AnswerOptions(
            question = domainQuestion,
            answer = question.toDomainOption()
        )
    }
}