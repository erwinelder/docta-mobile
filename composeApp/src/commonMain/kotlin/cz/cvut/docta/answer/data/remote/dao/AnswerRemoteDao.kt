package cz.cvut.docta.answer.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import cz.cvut.docta.answer.data.remote.model.AnswerOptionRemoteEntity
import cz.cvut.docta.answer.data.remote.model.BlankAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.CorrectOpenAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.PairTagPairRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.PairTagQuestionRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairRemoteEntity
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairTagRemoteEntity

@Dao
interface AnswerRemoteDao {

    @Upsert
    suspend fun upsertCorrectOpenAnswers(correctOpenAnswers: List<CorrectOpenAnswerRemoteEntity>)

    @Query("""
        SELECT * FROM correct_open_answer_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getCorrectOpenAnswersAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<CorrectOpenAnswerRemoteEntity>


    @Upsert
    suspend fun upsertBlanksAnswers(blanksAnswers: List<BlankAnswerRemoteEntity>)

    @Query("""
        SELECT * FROM blank_answer_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getBlanksAnswersAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<BlankAnswerRemoteEntity>


    @Upsert
    suspend fun upsertAnswerOptions(answerOptions: List<AnswerOptionRemoteEntity>)

    @Query("""
        SELECT * FROM answer_option_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getAnswerOptionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<AnswerOptionRemoteEntity>


    @Upsert
    suspend fun upsertPairTags(pairTags: List<QuestionAnswerPairTagRemoteEntity>)

    @Query("""
        SELECT * FROM question_answer_pair_tag_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getPairTagsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionAnswerPairTagRemoteEntity>


    @Upsert
    suspend fun upsertPairTagQuestionAssociations(associations: List<PairTagQuestionRemoteAssociation>)

    @Query("""
        SELECT * FROM pair_tag_question_remote_association
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getPairTagQuestionAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<PairTagQuestionRemoteAssociation>


    @Upsert
    suspend fun upsertPairTagPairAssociations(associations: List<PairTagPairRemoteAssociation>)

    @Query("""
        SELECT * FROM pair_tag_pair_remote_association
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getPairTagPairAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<PairTagPairRemoteAssociation>


    @Upsert
    suspend fun upsertQuestionAnswerPairs(questionAnswerPairs: List<QuestionAnswerPairRemoteEntity>)

    @Query("""
        SELECT * FROM question_answer_pair_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getQuestionAnswerPairsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionAnswerPairRemoteEntity>

}