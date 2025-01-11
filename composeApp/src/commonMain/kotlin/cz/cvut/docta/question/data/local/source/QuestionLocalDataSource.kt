package cz.cvut.docta.question.data.local.source

import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.data.local.model.entity.StepByStepLessonQuestionEntity
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.data.local.model.tag.QuestionTagDefaultLessonAssociation
import cz.cvut.docta.question.data.local.model.tag.QuestionTagEntity
import cz.cvut.docta.question.data.local.model.tag.QuestionTagQuestionAssociation

interface QuestionLocalDataSource {

    suspend fun getQuestionTagUpdateTime(courseCode: String): Long?

    suspend fun saveQuestionTagUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseQuestionTags(
        tagsToSync: EntitiesToSynchronise<QuestionTagEntity>,
        courseCode: String,
        timestamp: Long
    )


    suspend fun getQuestionTagDefaultLessonAssociationUpdateTime(courseCode: String): Long?

    suspend fun saveQuestionTagDefaultLessonAssociationUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseQuestionTagDefaultLessonAssociations(
        associationsToSync: EntitiesToSynchronise<QuestionTagDefaultLessonAssociation>,
        courseCode: String,
        timestamp: Long
    )


    suspend fun getQuestionTagQuestionAssociationUpdateTime(courseCode: String): Long?

    suspend fun saveQuestionTagQuestionAssociationUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseQuestionTagQuestionAssociations(
        associationsToSync: EntitiesToSynchronise<QuestionTagQuestionAssociation>,
        courseCode: String,
        timestamp: Long
    )


    suspend fun getQuestionUpdateTime(courseCode: String): Long?

    suspend fun saveQuestionUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseQuestions(
        questionsToSync: EntitiesToSynchronise<QuestionDetails>,
        courseCode: String,
        timestamp: Long
    )

    suspend fun getDefaultLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<QuestionDetails>


    suspend fun getStepByStepQuestionUpdateTime(courseCode: String): Long?

    suspend fun saveStepByStepQuestionUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseStepByStepLessonQuestions(
        questionsToSync: EntitiesToSynchronise<StepByStepLessonQuestionEntity>,
        courseCode: String,
        timestamp: Long
    )

    suspend fun getStepByStepLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.StepByStep
    ): List<StepByStepLessonQuestionEntity>

}