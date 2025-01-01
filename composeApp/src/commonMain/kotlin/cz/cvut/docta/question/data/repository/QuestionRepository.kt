package cz.cvut.docta.question.data.repository

import cz.cvut.docta.question.data.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.data.model.QuestionDetails
import cz.cvut.docta.question.data.model.StepByStepLessonQuestionEntity

interface QuestionRepository {

    suspend fun getDefaultLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<QuestionDetails>

    suspend fun getStepByStepLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.StepByStep
    ): List<StepByStepLessonQuestionEntity>

}