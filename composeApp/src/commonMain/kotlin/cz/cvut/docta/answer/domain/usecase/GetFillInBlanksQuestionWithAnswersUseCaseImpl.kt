package cz.cvut.docta.answer.domain.usecase

import cz.cvut.docta.answer.data.repository.AnswerRepository
import cz.cvut.docta.answer.mapper.toDomainBlanks
import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.domain.model.QuestionWithCorrectAnswers
import cz.cvut.docta.question.mapper.toDomain

class GetFillInBlanksQuestionWithAnswersUseCaseImpl(
    private val answerRepository: AnswerRepository,
    private val courseContext: CourseContext
) : GetFillInBlanksQuestionWithAnswersUseCase {
    override suspend fun execute(question: QuestionDetails.FillInBlanks): QuestionWithCorrectAnswers.FillInBlanks? {
        val answers = answerRepository.getBlanksAnswers(
            courseCode = courseContext.getCourseCode(), questionId = question.id
        )
        val domainQuestion = question.toDomain() ?: return null
        return QuestionWithCorrectAnswers.FillInBlanks(
            question = domainQuestion,
            blanksAnswers = answers.toDomainBlanks(questionId = question.id)
        )
    }
}