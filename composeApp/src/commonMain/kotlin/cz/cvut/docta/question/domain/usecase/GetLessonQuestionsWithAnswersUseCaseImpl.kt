package cz.cvut.docta.question.domain.usecase

import cz.cvut.docta.question.data.model.LessonQuestionsQueryOptions
import cz.cvut.docta.lesson.domain.usecase.GetLessonQuestionsQueryOptionsUseCase
import cz.cvut.docta.question.domain.model.QuestionWithAnswers

class GetLessonQuestionsWithAnswersUseCaseImpl(
    private val getLessonQuestionsQueryOptionsUseCase: GetLessonQuestionsQueryOptionsUseCase,
    private val getDefaultLessonQuestionsWithAnswersUseCase: GetDefaultLessonQuestionsWithAnswersUseCase,
    private val getStepByStepLessonQuestionsWithAnswersUseCase: GetStepByStepLessonQuestionsWithAnswersUseCase
) : GetLessonQuestionsWithAnswersUseCase {
    override suspend fun execute(lessonId: Long): List<QuestionWithAnswers> {
        val queryOptions = getLessonQuestionsQueryOptionsUseCase.execute(lessonId = lessonId)
            ?: return emptyList()

        return when (queryOptions) {
            is LessonQuestionsQueryOptions.Default ->
                getDefaultLessonQuestionsWithAnswersUseCase.execute(queryOptions = queryOptions)
            is LessonQuestionsQueryOptions.StepByStep ->
                getStepByStepLessonQuestionsWithAnswersUseCase.execute(queryOptions = queryOptions)
        }
    }
}