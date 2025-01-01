package cz.cvut.docta.question.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.question.data.model.AnswerOptionsQuestionWithDetails
import cz.cvut.docta.question.data.model.FillInBlanksQuestionWithDetails
import cz.cvut.docta.question.data.model.OpenAnswerQuestionWithDetails
import cz.cvut.docta.question.data.model.QuestionAnswerPairsQuestionWithDetails
import cz.cvut.docta.question.data.model.QuestionIdWithTag
import cz.cvut.docta.question.data.model.StepByStepLessonQuestionEntity

@Dao
interface QuestionDao {

    @Query("""
        SELECT question_tag_default_lesson_association.tag FROM question_tag_default_lesson_association
        WHERE lessonId = :lessonId
    """)
    suspend fun getTagsByLesson(lessonId: Long): List<String>

    @Query("""
        SELECT question_tag_question_association.questionId, question_tag_question_association.tag
        FROM question_tag_question_association
        WHERE question_tag_question_association.courseCode = :courseCode AND tag IN (:tags)
    """)
    suspend fun getQuestionsByCourseAndTags(
        courseCode: String,
        tags: List<String>
    ): List<QuestionIdWithTag>

    @Query("""
        SELECT * FROM question
        INNER JOIN open_answer_question ON question.id = open_answer_question.questionId
        WHERE question.id IN (:ids) AND question.difficulty = :difficulty
    """)
    suspend fun getOpenAnswerQuestions(
        ids: List<Long>,
        difficulty: String
    ): List<OpenAnswerQuestionWithDetails>

    @Query("""
        SELECT * FROM question
        INNER JOIN fill_in_blanks_question ON question.id = fill_in_blanks_question.questionId
        WHERE question.id IN (:ids) AND question.difficulty = :difficulty
    """)
    suspend fun getFillInBlanksQuestions(
        ids: List<Long>,
        difficulty: String
    ): List<FillInBlanksQuestionWithDetails>

    @Query("""
        SELECT * FROM question
        INNER JOIN answer_options_question ON question.id = answer_options_question.questionId
        WHERE question.id IN (:ids) AND question.difficulty = :difficulty
    """)
    suspend fun getAnswerOptionsQuestions(
        ids: List<Long>,
        difficulty: String
    ): List<AnswerOptionsQuestionWithDetails>

    @Query("""
        SELECT * FROM question
        INNER JOIN question_answer_pairs_question ON question.id = question_answer_pairs_question.questionId
        WHERE question.id IN (:ids) AND question.difficulty = :difficulty
    """)
    suspend fun getQuestionAnswerPairsQuestions(
        ids: List<Long>,
        difficulty: String
    ): List<QuestionAnswerPairsQuestionWithDetails>

    @Query("SELECT * FROM step_by_step_lesson_question WHERE lessonId = :lessonId")
    suspend fun getStepByStepLessonQuestions(lessonId: Long): List<StepByStepLessonQuestionEntity>

}