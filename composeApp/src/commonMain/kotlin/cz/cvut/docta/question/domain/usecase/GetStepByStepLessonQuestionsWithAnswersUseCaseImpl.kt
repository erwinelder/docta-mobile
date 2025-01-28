package cz.cvut.docta.question.domain.usecase

import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.data.repository.QuestionRepository
import cz.cvut.docta.question.domain.model.QuestionWithCorrectAnswers
import cz.cvut.docta.question.mapper.toDomainCorrectAnswer
import cz.cvut.docta.question.mapper.toDomainQuestion

class GetStepByStepLessonQuestionsWithAnswersUseCaseImpl(
    private val questionRepository: QuestionRepository
) : GetStepByStepLessonQuestionsWithAnswersUseCase {
    override suspend fun execute(
        queryOptions: LessonQuestionsQueryOptions.StepByStep
    ): List<QuestionWithCorrectAnswers.StepByStep> {
        return questionRepository.getStepByStepLessonQuestions(queryOptions = queryOptions)
            .map { question ->
                QuestionWithCorrectAnswers.StepByStep(
                    question = question.toDomainQuestion(),
                    answer = question.toDomainCorrectAnswer()
                )
            }
    }
}