package cz.cvut.docta.answer.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.answer.data.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.model.QuestionAnswerPairIdWithTag

@Dao
interface AnswerDao {

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

    @Query("SELECT * FROM correct_open_answer WHERE questionId = :questionId")
    suspend fun getOpenAnswers(questionId: Long): List<CorrectOpenAnswerEntity>

    @Query("SELECT * FROM blank_answer WHERE questionId = :questionId")
    suspend fun getBlanksAnswers(questionId: Long): List<BlankAnswerEntity>

    @Query("SELECT * FROM answer_option WHERE questionId = :questionId")
    suspend fun getAnswerOptions(questionId: Long): List<AnswerOptionEntity>

    @Query("""
        SELECT * FROM question_answer_pair
        WHERE id IN (:ids) AND difficulty = :difficulty
    """)
    suspend fun getQuestionAnswerPairs(
        ids: List<Long>,
        difficulty: String
    ): List<QuestionAnswerPairEntity>

}