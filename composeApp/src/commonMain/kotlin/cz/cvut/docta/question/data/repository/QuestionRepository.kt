package cz.cvut.docta.question.data.repository

import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.data.local.model.entity.StepByStepLessonQuestionEntity

interface QuestionRepository {

    suspend fun getDefaultLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<QuestionDetails>

    suspend fun getStepByStepLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.StepByStep
    ): List<StepByStepLessonQuestionEntity>

}