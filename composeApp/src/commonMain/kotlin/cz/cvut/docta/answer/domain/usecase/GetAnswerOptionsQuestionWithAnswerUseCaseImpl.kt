package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.answer.data.repository.AnswerRepository
import cz.cvut.docta.answer.mapper.toDomainOption
import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithCorrectAnswers
import cz.cvut.docta.question.mapper.toDomain

class GetAnswerOptionsQuestionWithAnswerUseCaseImpl(
    private val answerRepository: AnswerRepository,
    private val courseContext: CourseContext
) : GetAnswerOptionsQuestionWithAnswerUseCase {
    override suspend fun execute(
        question: QuestionDetails.AnswerOptions
    ): QuestionWithCorrectAnswers.AnswerOptions? {
        val options = answerRepository.getAnswerOptions(
            courseCode = courseContext.getCourseCode(), questionId = question.id
        )
        val domainQuestion = question.toDomain(options = options) ?: return null
        return QuestionWithCorrectAnswers.AnswerOptions(
            question = domainQuestion,
            answer = question.toDomainOption()
        )
    }
}