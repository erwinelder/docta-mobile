package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.answer.data.repository.AnswerRepository
import cz.cvut.docta.answer.mapper.toDomainOpenAnswers
import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithAnswers
import cz.cvut.docta.question.mapper.toDomain

class GetOpenAnswerQuestionWithAnswersUseCaseImpl(
    private val answerRepository: AnswerRepository,
    private val courseContext: CourseContext
) : GetOpenAnswerQuestionWithAnswersUseCase {
    override suspend fun execute(
        question: QuestionDetails.OpenAnswer
    ): QuestionWithAnswers.OpenAnswer? {
        val answers = answerRepository.getOpenAnswers(
            courseCode = courseContext.getCourseCode(), questionId = question.id
        )
        val domainQuestion = question.toDomain() ?: return null

        return QuestionWithAnswers.OpenAnswer(
            question = domainQuestion,
            answers = answers.toDomainOpenAnswers(questionId = question.id)
        )
    }
}