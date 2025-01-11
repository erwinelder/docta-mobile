package cz.cvut.docta.answer.data.repository

import cz.cvut.docta.answer.data.local.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.local.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.local.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairsQueryOptions

interface AnswerRepository {

    suspend fun getOpenAnswers(courseCode: String, questionId: Long): List<CorrectOpenAnswerEntity>

    suspend fun getBlanksAnswers(courseCode: String, questionId: Long): List<BlankAnswerEntity>

    suspend fun getAnswerOptions(courseCode: String, questionId: Long): List<AnswerOptionEntity>

    suspend fun getQuestionAnswerPairs(
        queryOptions: QuestionAnswerPairsQueryOptions
    ): List<QuestionAnswerPairEntity>

}