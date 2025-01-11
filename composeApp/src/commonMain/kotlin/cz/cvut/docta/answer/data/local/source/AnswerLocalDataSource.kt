package cz.cvut.docta.answer.data.local.source

import cz.cvut.docta.answer.data.local.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.local.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.local.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.local.model.PairTagPairAssociation
import cz.cvut.docta.answer.data.local.model.PairTagQuestionAssociation
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairTagEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairsQueryOptions
import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise

interface AnswerLocalDataSource {

    suspend fun getCorrectOpenAnswerUpdateTime(courseCode: String): Long?

    suspend fun saveOpenAnswerUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseOpenAnswers(
        answersToSync: EntitiesToSynchronise<CorrectOpenAnswerEntity>,
        courseCode: String,
        timestamp: Long
    )

    suspend fun getOpenAnswers(questionId: Long): List<CorrectOpenAnswerEntity>


    suspend fun getBlankAnswerUpdateTime(courseCode: String): Long?

    suspend fun saveBlankAnswerUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseBlanksAnswers(
        answersToSync: EntitiesToSynchronise<BlankAnswerEntity>,
        courseCode: String,
        timestamp: Long
    )

    suspend fun getBlanksAnswers(questionId: Long): List<BlankAnswerEntity>


    suspend fun getAnswerOptionUpdateTime(courseCode: String): Long?

    suspend fun saveAnswerOptionUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseAnswerOptions(
        answersToSync: EntitiesToSynchronise<AnswerOptionEntity>,
        courseCode: String,
        timestamp: Long
    )

    suspend fun getAnswerOptions(questionId: Long): List<AnswerOptionEntity>


    suspend fun getPairTagUpdateTime(courseCode: String): Long?

    suspend fun savePairTagUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchronisePairTags(
        tagsToSync: EntitiesToSynchronise<QuestionAnswerPairTagEntity>,
        courseCode: String,
        timestamp: Long
    )


    suspend fun getPairTagQuestionAssociationUpdateTime(courseCode: String): Long?

    suspend fun savePairTagQuestionAssociationUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchronisePairTagQuestionAssociations(
        associationsToSync: EntitiesToSynchronise<PairTagQuestionAssociation>,
        courseCode: String,
        timestamp: Long
    )


    suspend fun getPairTagPairAssociationUpdateTime(courseCode: String): Long?

    suspend fun savePairTagPairAssociationUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchronisePairTagPairAssociations(
        associationsToSync: EntitiesToSynchronise<PairTagPairAssociation>,
        courseCode: String,
        timestamp: Long
    )


    suspend fun getQuestionAnswerPairUpdateTime(courseCode: String): Long?

    suspend fun saveQuestionAnswerPairUpdateTime(courseCode: String, timestamp: Long)

    suspend fun synchroniseQuestionAnswerPairs(
        pairsToSync: EntitiesToSynchronise<QuestionAnswerPairEntity>,
        courseCode: String,
        timestamp: Long
    )

    suspend fun getQuestionAnswerPairs(
        queryOptions: QuestionAnswerPairsQueryOptions
    ): List<QuestionAnswerPairEntity>

}