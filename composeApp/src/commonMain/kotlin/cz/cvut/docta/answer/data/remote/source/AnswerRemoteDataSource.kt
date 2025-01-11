package cz.cvut.docta.answer.data.remote.source

import cz.cvut.docta.answer.data.remote.model.AnswerOptionRemoteEntity
import cz.cvut.docta.answer.data.remote.model.BlankAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.CorrectOpenAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.PairTagPairRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.PairTagQuestionRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairRemoteEntity
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairTagRemoteEntity

interface AnswerRemoteDataSource {

    suspend fun getCorrectOpenAnswerUpdateTime(courseCode: String): Long?

    suspend fun saveCorrectOpenAnswerUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getCorrectOpenAnswersAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<CorrectOpenAnswerRemoteEntity>


    suspend fun getBlankAnswerUpdateTime(courseCode: String): Long?

    suspend fun saveBlankAnswerUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getBlankAnswersAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<BlankAnswerRemoteEntity>


    suspend fun getAnswerOptionUpdateTime(courseCode: String): Long?

    suspend fun saveAnswerOptionUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getAnswerOptionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<AnswerOptionRemoteEntity>


    suspend fun getPairTagUpdateTime(courseCode: String): Long?

    suspend fun savePairTagUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getPairTagsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionAnswerPairTagRemoteEntity>


    suspend fun getPairTagQuestionAssociationUpdateTime(courseCode: String): Long?

    suspend fun savePairTagQuestionAssociationUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getPairTagQuestionAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<PairTagQuestionRemoteAssociation>


    suspend fun getPairTagPairAssociationUpdateTime(courseCode: String): Long?

    suspend fun savePairTagPairAssociationUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getPairTagPairAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<PairTagPairRemoteAssociation>


    suspend fun getQuestionAnswerPairUpdateTime(courseCode: String): Long?

    suspend fun saveQuestionAnswerPairUpdateTime(courseCode: String, timestamp: Long)

    suspend fun getQuestionAnswerPairsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionAnswerPairRemoteEntity>

}