package cz.cvut.docta.answer.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.answer.data.remote.model.AnswerOptionRemoteEntity
import cz.cvut.docta.answer.data.remote.model.BlankAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.CorrectOpenAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.PairTagPairRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.PairTagQuestionRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairRemoteEntity
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairTagRemoteEntity

@Dao
interface AnswerRemoteDao {

    @Query("""
        SELECT * FROM correct_open_answer_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getCorrectOpenAnswersAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<CorrectOpenAnswerRemoteEntity>

    @Query("""
        SELECT * FROM blank_answer_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getBlanksAnswersAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<BlankAnswerRemoteEntity>

    @Query("""
        SELECT * FROM answer_option_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getAnswerOptionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<AnswerOptionRemoteEntity>

    @Query("""
        SELECT * FROM question_answer_pair_tag_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getPairTagsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionAnswerPairTagRemoteEntity>

    @Query("""
        SELECT * FROM pair_tag_question_remote_association
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getPairTagQuestionAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<PairTagQuestionRemoteAssociation>

    @Query("""
        SELECT * FROM pair_tag_pair_remote_association
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getPairTagPairAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<PairTagPairRemoteAssociation>

    @Query("""
        SELECT * FROM question_answer_pair_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getQuestionAnswerPairsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionAnswerPairRemoteEntity>

}