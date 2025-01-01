package cz.cvut.docta.question.data.local.source

import cz.cvut.docta.question.data.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.data.model.QuestionDetails
import cz.cvut.docta.question.data.model.StepByStepLessonQuestionEntity

interface QuestionLocalDataSource {

    suspend fun getDefaultLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<QuestionDetails>

    suspend fun getStepByStepLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.StepByStep
    ): List<StepByStepLessonQuestionEntity>

}