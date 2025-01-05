package cz.cvut.docta.question.data.repository

import cz.cvut.docta.question.data.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.data.model.QuestionDetails
import cz.cvut.docta.question.data.local.source.QuestionLocalDataSource
import cz.cvut.docta.question.data.model.StepByStepLessonQuestionEntity

class QuestionRepositoryImpl(
    private val questionLocalDataSource: QuestionLocalDataSource
) : QuestionRepository {

    override suspend fun getDefaultLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<QuestionDetails> {
        return questionLocalDataSource.getDefaultLessonQuestions(queryOptions = queryOptions)
    }

    override suspend fun getStepByStepLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.StepByStep
    ): List<StepByStepLessonQuestionEntity> {
        return questionLocalDataSource.getStepByStepLessonQuestions(queryOptions = queryOptions)
    }

}