package cz.cvut.docta.answer.data.local.source

import cz.cvut.docta.answer.data.local.dao.AnswerDao
import cz.cvut.docta.answer.data.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.model.QuestionAnswerPairsQueryOptions
import cz.cvut.docta.core.data.local.AppLocalDatabase

class AnswerLocalDataSourceImpl(
    private val dao: AnswerDao
) : AnswerLocalDataSource {

    override suspend fun getOpenAnswers(questionId: Long): List<CorrectOpenAnswerEntity> {
        return dao.getOpenAnswers(questionId)
    }

    override suspend fun getBlanksAnswers(questionId: Long): List<BlankAnswerEntity> {
        return dao.getBlanksAnswers(questionId)
    }

    override suspend fun getAnswerOptions(questionId: Long): List<AnswerOptionEntity> {
        return dao.getAnswerOptions(questionId)
    }

    override suspend fun getQuestionAnswerPairs(
        queryOptions: QuestionAnswerPairsQueryOptions
    ): List<QuestionAnswerPairEntity> {
        val tags = dao.getTagsByQuestion(questionId = queryOptions.questionId)
        val pairsWithTags = dao.getQuestionAnswerPairsByCourseAndTags(
            courseCode = queryOptions.courseCode, tags = tags
        )

        val pairTags = pairsWithTags
            .groupBy { it.pairId }
            .mapValues { it.value.map { pairIdWithTag -> pairIdWithTag.tag } }
        val pairsIds = pairTags.keys.toList()

        return dao.getQuestionAnswerPairs(
            ids = pairsIds,
            difficulty = queryOptions.difficulty
        )
    }

}

fun answerLocalDataSourceFactory(
    appLocalDatabase: AppLocalDatabase
): AnswerLocalDataSource {
    return AnswerLocalDataSourceImpl(dao = appLocalDatabase.answerDao())
}