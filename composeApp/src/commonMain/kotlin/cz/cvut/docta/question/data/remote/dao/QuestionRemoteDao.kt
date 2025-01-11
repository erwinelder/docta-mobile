package cz.cvut.docta.question.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.question.data.remote.model.entity.StepByStepLessonQuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.entity_with_details.AnswerOptionsQuestionRemoteWithDetails
import cz.cvut.docta.question.data.remote.model.entity_with_details.FillInBlanksQuestionRemoteWithDetails
import cz.cvut.docta.question.data.remote.model.entity_with_details.OpenAnswerQuestionRemoteWithDetails
import cz.cvut.docta.question.data.remote.model.entity_with_details.QuestionAnswerPairsQuestionRemoteWithDetails
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagDefaultLessonRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagQuestionRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagRemoteEntity

@Dao
interface QuestionRemoteDao {

    @Query("""
        SELECT * FROM question_tag_remote
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getQuestionTagsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionTagRemoteEntity>

    @Query("""
        SELECT * FROM question_tag_default_lesson_remote_association
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getQuestionTagDefaultLessonAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionTagDefaultLessonRemoteAssociation>

    @Query("""
        SELECT * FROM question_tag_question_remote_association
        WHERE courseCode = :courseCode AND updateTime > :timestamp
    """)
    suspend fun getQuestionTagQuestionAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionTagQuestionRemoteAssociation>


    @Query("""
        SELECT * FROM question_remote
        INNER JOIN open_answer_question_remote ON question_remote.id = open_answer_question_remote.questionId
        WHERE question_remote.courseCode = :courseCode AND question_remote.updateTime > :timestamp
    """)
    suspend fun getOpenAnswerQuestionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<OpenAnswerQuestionRemoteWithDetails>

    @Query("""
        SELECT * FROM question_remote
        INNER JOIN fill_in_blanks_question_remote ON question_remote.id = fill_in_blanks_question_remote.questionId
        WHERE question_remote.courseCode = :courseCode AND question_remote.updateTime > :timestamp
    """)
    suspend fun getFillInBlanksQuestionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<FillInBlanksQuestionRemoteWithDetails>

    @Query("""
        SELECT * FROM question_remote
        INNER JOIN answer_options_question_remote ON question_remote.id = answer_options_question_remote.questionId
        WHERE question_remote.courseCode = :courseCode AND question_remote.updateTime > :timestamp
    """)
    suspend fun getAnswerOptionsQuestionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<AnswerOptionsQuestionRemoteWithDetails>

    @Query("""
        SELECT * FROM question_remote
        INNER JOIN question_answer_pairs_question_remote ON question_remote.id = question_answer_pairs_question_remote.questionId
        WHERE question_remote.courseCode = :courseCode AND question_remote.updateTime > :timestamp
    """)
    suspend fun getQuestionAnswerPairsQuestionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionAnswerPairsQuestionRemoteWithDetails>

    @Query("""
        SELECT * FROM step_by_step_lesson_question_remote
        WHERE step_by_step_lesson_question_remote.courseCode = :courseCode AND step_by_step_lesson_question_remote.updateTime > :timestamp
    """)
    suspend fun getStepByStepLessonQuestionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<StepByStepLessonQuestionRemoteEntity>

}