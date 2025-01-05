package cz.cvut.docta.answer.data.repository

import cz.cvut.docta.answer.data.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.local.source.AnswerLocalDataSource
import cz.cvut.docta.answer.data.model.QuestionAnswerPairsQueryOptions

class AnswerRepositoryImpl(
    private val localDataSource: AnswerLocalDataSource
) : AnswerRepository {

    override suspend fun getOpenAnswers(questionId: Long): List<CorrectOpenAnswerEntity> {
        return localDataSource.getOpenAnswers(questionId = questionId)
    }

    override suspend fun getBlanksAnswers(questionId: Long): List<BlankAnswerEntity> {
        return localDataSource.getBlanksAnswers(questionId = questionId)
    }

    override suspend fun getAnswerOptions(questionId: Long): List<AnswerOptionEntity> {
        return localDataSource.getAnswerOptions(questionId = questionId)
    }

    override suspend fun getQuestionAnswerPairs(
        queryOptions: QuestionAnswerPairsQueryOptions
    ): List<QuestionAnswerPairEntity> {
        return localDataSource.getQuestionAnswerPairs(queryOptions = queryOptions)
    }

}