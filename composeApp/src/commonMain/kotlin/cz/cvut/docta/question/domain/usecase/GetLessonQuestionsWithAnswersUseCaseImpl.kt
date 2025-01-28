package cz.cvut.docta.question.domain.usecase

import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions
import cz.cvut.docta.lesson.domain.usecase.GetQuestionsQueryOptionsUseCase
import cz.cvut.docta.question.domain.model.QuestionWithCorrectAnswers

class GetLessonQuestionsWithAnswersUseCaseImpl(
    private val getQuestionsQueryOptionsUseCase: GetQuestionsQueryOptionsUseCase,
    private val getDefaultLessonQuestionsWithAnswersUseCase: GetDefaultLessonQuestionsWithAnswersUseCase,
    private val getStepByStepLessonQuestionsWithAnswersUseCase: GetStepByStepLessonQuestionsWithAnswersUseCase
) : GetLessonQuestionsWithAnswersUseCase {
    override suspend fun execute(lessonId: Long): List<QuestionWithCorrectAnswers> {
        val queryOptions = getQuestionsQueryOptionsUseCase.execute(lessonId = lessonId)
            ?: return emptyList()

        return when (queryOptions) {
            is LessonQuestionsQueryOptions.Default ->
                getDefaultLessonQuestionsWithAnswersUseCase.execute(queryOptions = queryOptions)
            is LessonQuestionsQueryOptions.StepByStep ->
                getStepByStepLessonQuestionsWithAnswersUseCase.execute(queryOptions = queryOptions)
        }
    }
}