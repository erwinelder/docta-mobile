package cz.cvut.docta.answer.data.repository

import cz.cvut.docta.answer.data.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.model.QuestionAnswerPairsQueryOptions

interface AnswerRepository {

    suspend fun getOpenAnswers(questionId: Long): List<CorrectOpenAnswerEntity>

    suspend fun getBlanksAnswers(questionId: Long): List<BlankAnswerEntity>

    suspend fun getAnswerOptions(questionId: Long): List<AnswerOptionEntity>

    suspend fun getQuestionAnswerPairs(
        queryOptions: QuestionAnswerPairsQueryOptions
    ): List<QuestionAnswerPairEntity>

}