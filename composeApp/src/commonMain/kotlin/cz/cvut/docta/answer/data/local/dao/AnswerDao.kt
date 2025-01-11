package cz.cvut.docta.answer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import cz.cvut.docta.answer.data.local.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.local.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.local.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.local.model.PairTagPairAssociation
import cz.cvut.docta.answer.data.local.model.PairTagQuestionAssociation
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairIdWithTag
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairTagEntity

@Dao
interface AnswerDao {

    @Upsert
    suspend fun upsertOpenAnswers(answers: List<CorrectOpenAnswerEntity>)

    @Delete
    suspend fun deleteOpenAnswers(answers: List<CorrectOpenAnswerEntity>)

    @Transaction
    suspend fun deleteAndUpsertOpenAnswers(
        toDelete: List<CorrectOpenAnswerEntity>,
        toUpsert: List<CorrectOpenAnswerEntity>
    ) {
        deleteOpenAnswers(toDelete)
        upsertOpenAnswers(toUpsert)
    }

    @Query("SELECT * FROM correct_open_answer WHERE questionId = :questionId")
    suspend fun getOpenAnswers(questionId: Long): List<CorrectOpenAnswerEntity>


    @Upsert
    suspend fun upsertBlanksAnswers(answers: List<BlankAnswerEntity>)

    @Delete
    suspend fun deleteBlanksAnswers(answers: List<BlankAnswerEntity>)

    @Transaction
    suspend fun deleteAndUpsertBlanksAnswers(
        toDelete: List<BlankAnswerEntity>,
        toUpsert: List<BlankAnswerEntity>
    ) {
        deleteBlanksAnswers(toDelete)
        upsertBlanksAnswers(toUpsert)
    }

    @Query("SELECT * FROM blank_answer WHERE questionId = :questionId")
    suspend fun getBlanksAnswers(questionId: Long): List<BlankAnswerEntity>


    @Upsert
    suspend fun upsertAnswerOptions(options: List<AnswerOptionEntity>)

    @Delete
    suspend fun deleteAnswerOptions(options: List<AnswerOptionEntity>)

    @Transaction
    suspend fun deleteAndUpsertAnswerOptions(
        toDelete: List<AnswerOptionEntity>,
        toUpsert: List<AnswerOptionEntity>
    ) {
        deleteAnswerOptions(toDelete)
        upsertAnswerOptions(toUpsert)
    }

    @Query("SELECT * FROM answer_option WHERE questionId = :questionId")
    suspend fun getAnswerOptions(questionId: Long): List<AnswerOptionEntity>


    @Upsert
    suspend fun upsertPairTags(tags: List<QuestionAnswerPairTagEntity>)

    @Delete
    suspend fun deletePairTags(tags: List<QuestionAnswerPairTagEntity>)

    @Transaction
    suspend fun deleteAndUpsertPairTags(
        toDelete: List<QuestionAnswerPairTagEntity>,
        toUpsert: List<QuestionAnswerPairTagEntity>
    ) {
        deletePairTags(toDelete)
        upsertPairTags(toUpsert)
    }


    @Upsert
    suspend fun upsertPairTagQuestionAssociations(associations: List<PairTagQuestionAssociation>)

    @Delete
    suspend fun deletePairTagQuestionAssociations(associations: List<PairTagQuestionAssociation>)

    @Transaction
    suspend fun deleteAndUpsertPairTagQuestionAssociations(
        toDelete: List<PairTagQuestionAssociation>,
        toUpsert: List<PairTagQuestionAssociation>
    ) {
        deletePairTagQuestionAssociations(toDelete)
        upsertPairTagQuestionAssociations(toUpsert)
    }


    @Upsert
    suspend fun upsertPairTagPairAssociations(associations: List<PairTagPairAssociation>)

    @Delete
    suspend fun deletePairTagPairAssociations(associations: List<PairTagPairAssociation>)

    @Transaction
    suspend fun deleteAndUpsertPairTagPairAssociations(
        toDelete: List<PairTagPairAssociation>,
        toUpsert: List<PairTagPairAssociation>
    ) {
        deletePairTagPairAssociations(toDelete)
        upsertPairTagPairAssociations(toUpsert)
    }


    @Query("""
        SELECT pair_tag_question_association.tag FROM pair_tag_question_association
        WHERE questionId = :questionId
    """)
    suspend fun getTagsByQuestion(questionId: Long): List<String>

    @Query("""
        SELECT pair_tag_pair_association.pairId, pair_tag_pair_association.tag
        FROM pair_tag_pair_association
        WHERE pair_tag_pair_association.courseCode = :courseCode AND tag IN (:tags)
    """)
    suspend fun getQuestionAnswerPairsByCourseAndTags(
        courseCode: String,
        tags: List<String>
    ): List<QuestionAnswerPairIdWithTag>


    @Upsert
    suspend fun upsertQuestionAnswerPairs(pairs: List<QuestionAnswerPairEntity>)

    @Delete
    suspend fun deleteQuestionAnswerPairs(pairs: List<QuestionAnswerPairEntity>)

    @Transaction
    suspend fun deleteAndUpsertQuestionAnswerPairs(
        toDelete: List<QuestionAnswerPairEntity>,
        toUpsert: List<QuestionAnswerPairEntity>
    ) {
        deleteQuestionAnswerPairs(toDelete)
        upsertQuestionAnswerPairs(toUpsert)
    }

    @Query("""
        SELECT * FROM question_answer_pair
        WHERE id IN (:ids) AND difficulty = :difficulty
    """)
    suspend fun getQuestionAnswerPairs(
        ids: List<Long>,
        difficulty: String
    ): List<QuestionAnswerPairEntity>

}