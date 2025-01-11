package cz.cvut.docta.question.data.remote.source

import cz.cvut.docta.question.data.remote.model.QuestionRemoteDetails
import cz.cvut.docta.question.data.remote.model.entity.StepByStepLessonQuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagDefaultLessonRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagQuestionRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagRemoteEntity

interface QuestionRemoteDataSource {

    suspend fun getQuestionTagUpdateTime(courseCode: String): Long?

    suspend fun saveQuestionTagUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getQuestionTagsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionTagRemoteEntity>


    suspend fun getQuestionTagDefaultLessonAssociationUpdateTime(courseCode: String): Long?

    suspend fun saveQuestionTagDefaultLessonAssociationUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getQuestionTagDefaultLessonAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionTagDefaultLessonRemoteAssociation>


    suspend fun getQuestionTagQuestionAssociationUpdateTime(courseCode: String): Long?

    suspend fun saveQuestionTagQuestionAssociationUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getQuestionTagQuestionAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionTagQuestionRemoteAssociation>


    suspend fun getQuestionUpdateTime(courseCode: String): Long?

    suspend fun saveQuestionUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getQuestionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionRemoteDetails>


    suspend fun getStepByStepQuestionUpdateTime(courseCode: String): Long?

    suspend fun saveStepByStepQuestionUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getStepByStepQuestionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<StepByStepLessonQuestionRemoteEntity>

}