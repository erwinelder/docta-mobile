package cz.cvut.docta.question.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import cz.cvut.docta.question.data.local.model.entity.AnswerOptionsQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.FillInBlanksQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.OpenAnswerQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.QuestionAnswerPairsQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.QuestionEntity
import cz.cvut.docta.question.data.local.model.entity_with_details.AnswerOptionsQuestionWithDetails
import cz.cvut.docta.question.data.local.model.entity_with_details.FillInBlanksQuestionWithDetails
import cz.cvut.docta.question.data.local.model.entity_with_details.OpenAnswerQuestionWithDetails
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionAnswerPairsQuestionWithDetails
import cz.cvut.docta.question.data.local.model.tag.QuestionIdWithTag
import cz.cvut.docta.question.data.local.model.entity.StepByStepLessonQuestionEntity
import cz.cvut.docta.question.data.local.model.tag.QuestionTagDefaultLessonAssociation
import cz.cvut.docta.question.data.local.model.tag.QuestionTagEntity
import cz.cvut.docta.question.data.local.model.tag.QuestionTagQuestionAssociation

@Dao
interface QuestionDao {

    @Upsert
    suspend fun upsertQuestionTags(tags: List<QuestionTagEntity>)

    @Delete
    suspend fun deleteQuestionTags(tags: List<QuestionTagEntity>)

    @Transaction
    suspend fun deleteAndUpsertQuestionTags(
        toDelete: List<QuestionTagEntity>,
        toUpsert: List<QuestionTagEntity>
    ) {
        deleteQuestionTags(toDelete)
        upsertQuestionTags(toUpsert)
    }

    @Query("""
        SELECT question_tag_default_lesson_association.tag FROM question_tag_default_lesson_association
        WHERE lessonId = :lessonId
    """)
    suspend fun getTagsOfLesson(lessonId: Long): List<String>

    @Query("""
        SELECT question_tag_question_association.questionId, question_tag_question_association.tag
        FROM question_tag_question_association
        WHERE question_tag_question_association.courseCode = :courseCode AND tag IN (:tags)
    """)
    suspend fun getQuestionsByCourseAndTags(
        courseCode: String,
        tags: List<String>
    ): List<QuestionIdWithTag>


    @Upsert
    suspend fun upsertQuestionTagDefaultLessonAssociations(associations: List<QuestionTagDefaultLessonAssociation>)

    @Delete
    suspend fun deleteQuestionTagDefaultLessonAssociations(associations: List<QuestionTagDefaultLessonAssociation>)

    @Transaction
    suspend fun deleteAndUpsertQuestionTagDefaultLessonAssociations(
        toDelete: List<QuestionTagDefaultLessonAssociation>,
        toUpsert: List<QuestionTagDefaultLessonAssociation>
    ) {
        deleteQuestionTagDefaultLessonAssociations(toDelete)
        upsertQuestionTagDefaultLessonAssociations(toUpsert)
    }


    @Upsert
    suspend fun upsertQuestionTagQuestionAssociations(associations: List<QuestionTagQuestionAssociation>)

    @Delete
    suspend fun deleteQuestionTagQuestionAssociations(associations: List<QuestionTagQuestionAssociation>)

    @Transaction
    suspend fun deleteAndUpsertQuestionTagQuestionAssociations(
        toDelete: List<QuestionTagQuestionAssociation>,
        toUpsert: List<QuestionTagQuestionAssociation>
    ) {
        deleteQuestionTagQuestionAssociations(toDelete)
        upsertQuestionTagQuestionAssociations(toUpsert)
    }


    @Upsert
    suspend fun upsertQuestions(questions: List<QuestionEntity>)

    @Delete
    suspend fun deleteQuestions(questions: List<QuestionEntity>)

    @Transaction
    suspend fun deleteAndUpsertQuestionsAndInheritedQuestions(
        questionsToDelete: List<QuestionEntity>,
        questionsToUpsert: List<QuestionEntity>,
        openAnswerQuestionsToUpsert: List<OpenAnswerQuestionEntity>,
        fillInBlanksQuestionsToUpsert: List<FillInBlanksQuestionEntity>,
        answerOptionsQuestionsToUpsert: List<AnswerOptionsQuestionEntity>,
        questionAnswerPairsQuestionsToUpsert: List<QuestionAnswerPairsQuestionEntity>
    ) {
        deleteQuestions(questionsToDelete)
        upsertQuestions(questionsToUpsert)

        upsertOpenAnswerQuestions(openAnswerQuestionsToUpsert)
        upsertFillInBlanksQuestions(fillInBlanksQuestionsToUpsert)
        upsertAnswerOptionsQuestions(answerOptionsQuestionsToUpsert)
        upsertQuestionAnswerPairsQuestions(questionAnswerPairsQuestionsToUpsert)
    }


    @Upsert
    suspend fun upsertOpenAnswerQuestions(questions: List<OpenAnswerQuestionEntity>)

    @Query("""
        SELECT * FROM question
        INNER JOIN open_answer_question ON question.id = open_answer_question.questionId
        WHERE question.id IN (:ids) AND question.difficulty = :difficulty
    """)
    suspend fun getOpenAnswerQuestions(
        ids: List<Long>,
        difficulty: String
    ): List<OpenAnswerQuestionWithDetails>


    @Upsert
    suspend fun upsertFillInBlanksQuestions(questions: List<FillInBlanksQuestionEntity>)

    @Query("""
        SELECT * FROM question
        INNER JOIN fill_in_blanks_question ON question.id = fill_in_blanks_question.questionId
        WHERE question.id IN (:ids) AND question.difficulty = :difficulty
    """)
    suspend fun getFillInBlanksQuestions(
        ids: List<Long>,
        difficulty: String
    ): List<FillInBlanksQuestionWithDetails>


    @Upsert
    suspend fun upsertAnswerOptionsQuestions(questions: List<AnswerOptionsQuestionEntity>)

    @Query("""
        SELECT * FROM question
        INNER JOIN answer_options_question ON question.id = answer_options_question.questionId
        WHERE question.id IN (:ids) AND question.difficulty = :difficulty
    """)
    suspend fun getAnswerOptionsQuestions(
        ids: List<Long>,
        difficulty: String
    ): List<AnswerOptionsQuestionWithDetails>


    @Upsert
    suspend fun upsertQuestionAnswerPairsQuestions(questions: List<QuestionAnswerPairsQuestionEntity>)

    @Query("""
        SELECT * FROM question
        INNER JOIN question_answer_pairs_question ON question.id = question_answer_pairs_question.questionId
        WHERE question.id IN (:ids) AND question.difficulty = :difficulty
    """)
    suspend fun getQuestionAnswerPairsQuestions(
        ids: List<Long>,
        difficulty: String
    ): List<QuestionAnswerPairsQuestionWithDetails>


    @Upsert
    suspend fun upsertStepByStepLessonQuestions(questions: List<StepByStepLessonQuestionEntity>)

    @Delete
    suspend fun deleteStepByStepLessonQuestions(questions: List<StepByStepLessonQuestionEntity>)

    @Transaction
    suspend fun deleteAndUpsertStepByStepLessonQuestions(
        toDelete: List<StepByStepLessonQuestionEntity>,
        toUpsert: List<StepByStepLessonQuestionEntity>
    ) {
        deleteStepByStepLessonQuestions(toDelete)
        upsertStepByStepLessonQuestions(toUpsert)
    }

    @Query("SELECT * FROM step_by_step_lesson_question WHERE lessonId = :lessonId")
    suspend fun getStepByStepLessonQuestions(lessonId: Long): List<StepByStepLessonQuestionEntity>

}