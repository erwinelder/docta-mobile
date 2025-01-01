package cz.cvut.docta.question.domain.usecase

import cz.cvut.docta.question.data.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.domain.model.QuestionWithAnswers

interface GetStepByStepLessonQuestionsWithAnswersUseCase {
    suspend fun execute(
        queryOptions: LessonQuestionsQueryOptions.StepByStep
    ): List<QuestionWithAnswers.StepByStep>
}