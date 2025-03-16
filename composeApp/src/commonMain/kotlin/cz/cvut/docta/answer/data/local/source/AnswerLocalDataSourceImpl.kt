package cz.cvut.docta.answer.data.local.source

import cz.cvut.docta.answer.data.local.dao.AnswerDao
import cz.cvut.docta.answer.data.local.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.local.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.local.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.local.model.PairTagPairAssociation
import cz.cvut.docta.answer.data.local.model.PairTagQuestionAssociation
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairTagEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairsQueryOptions
import cz.cvut.docta.core.data.database.AppLocalDatabase
import cz.cvut.docta.core.data.local.dao.LocalUpdateTimeDao
import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.core.data.model.TableName

class AnswerLocalDataSourceImpl(
    private val answerDao: AnswerDao,
    private val updateTimeDao: LocalUpdateTimeDao
) : AnswerLocalDataSource {

    override suspend fun getCorrectOpenAnswerUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.CorrectOpenAnswer.name, courseCode = courseCode
        )
    }

    override suspend fun saveOpenAnswerUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.CorrectOpenAnswer.name,
            updateTime = timestamp,
            courseCode = courseCode
        )
    }

    override suspend fun synchroniseOpenAnswers(
        answersToSync: EntitiesToSynchronise<CorrectOpenAnswerEntity>,
        courseCode: String,
        timestamp: Long
    ) {
        answerDao.deleteAndUpsertOpenAnswers(
            toDelete = answersToSync.toDelete, toUpsert = answersToSync.toUpsert
        )
    }

    override suspend fun getOpenAnswers(questionId: Long): List<CorrectOpenAnswerEntity> {
        return answerDao.getOpenAnswers(questionId)
    }


    override suspend fun getBlankAnswerUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.BlankAnswer.name, courseCode = courseCode
        )
    }

    override suspend fun saveBlankAnswerUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.BlankAnswer.name, updateTime = timestamp, courseCode = courseCode
        )
    }

    override suspend fun synchroniseBlanksAnswers(
        answersToSync: EntitiesToSynchronise<BlankAnswerEntity>,
        courseCode: String,
        timestamp: Long
    ) {
        answerDao.deleteAndUpsertBlanksAnswers(
            toDelete = answersToSync.toDelete, toUpsert = answersToSync.toUpsert
        )
    }

    override suspend fun getBlanksAnswers(questionId: Long): List<BlankAnswerEntity> {
        return answerDao.getBlanksAnswers(questionId)
    }


    override suspend fun getAnswerOptionUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.AnswerOption.name, courseCode = courseCode
        )
    }

    override suspend fun saveAnswerOptionUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.AnswerOption.name, updateTime = timestamp, courseCode = courseCode
        )
    }

    override suspend fun synchroniseAnswerOptions(
        answersToSync: EntitiesToSynchronise<AnswerOptionEntity>,
        courseCode: String,
        timestamp: Long
    ) {
        answerDao.deleteAndUpsertAnswerOptions(
            toDelete = answersToSync.toDelete, toUpsert = answersToSync.toUpsert
        )
    }

    override suspend fun getAnswerOptions(questionId: Long): List<AnswerOptionEntity> {
        return answerDao.getAnswerOptions(questionId)
    }


    override suspend fun getPairTagUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.QuestionAnswerPairTag.name, courseCode = courseCode
        )
    }

    override suspend fun savePairTagUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.QuestionAnswerPairTag.name,
            updateTime = timestamp,
            courseCode = courseCode
        )
    }

    override suspend fun synchronisePairTags(
        tagsToSync: EntitiesToSynchronise<QuestionAnswerPairTagEntity>,
        courseCode: String,
        timestamp: Long
    ) {
        answerDao.deleteAndUpsertPairTags(
            toDelete = tagsToSync.toDelete, toUpsert = tagsToSync.toUpsert
        )
    }


    override suspend fun getPairTagQuestionAssociationUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.PairTagQuestionAssociation.name, courseCode = courseCode
        )
    }

    override suspend fun savePairTagQuestionAssociationUpdateTime(
        courseCode: String,
        timestamp: Long
    ) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.PairTagQuestionAssociation.name,
            updateTime = timestamp,
            courseCode = courseCode
        )
    }

    override suspend fun synchronisePairTagQuestionAssociations(
        associationsToSync: EntitiesToSynchronise<PairTagQuestionAssociation>,
        courseCode: String,
        timestamp: Long
    ) {
        answerDao.deleteAndUpsertPairTagQuestionAssociations(
            toDelete = associationsToSync.toDelete, toUpsert = associationsToSync.toUpsert
        )
    }


    override suspend fun getPairTagPairAssociationUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.PairTagPairAssociation.name, courseCode = courseCode
        )
    }

    override suspend fun savePairTagPairAssociationUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.PairTagPairAssociation.name,
            updateTime = timestamp,
            courseCode = courseCode
        )
    }

    override suspend fun synchronisePairTagPairAssociations(
        associationsToSync: EntitiesToSynchronise<PairTagPairAssociation>,
        courseCode: String,
        timestamp: Long
    ) {
        answerDao.deleteAndUpsertPairTagPairAssociations(
            toDelete = associationsToSync.toDelete, toUpsert = associationsToSync.toUpsert
        )
    }


    override suspend fun getQuestionAnswerPairUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.QuestionAnswerPair.name, courseCode = courseCode
        )
    }

    override suspend fun saveQuestionAnswerPairUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.QuestionAnswerPair.name,
            updateTime = timestamp,
            courseCode = courseCode
        )
    }

    override suspend fun synchroniseQuestionAnswerPairs(
        pairsToSync: EntitiesToSynchronise<QuestionAnswerPairEntity>,
        courseCode: String,
        timestamp: Long
    ) {
        answerDao.deleteAndUpsertQuestionAnswerPairs(
            toDelete = pairsToSync.toDelete, toUpsert = pairsToSync.toUpsert
        )
    }

    override suspend fun getQuestionAnswerPairs(
        queryOptions: QuestionAnswerPairsQueryOptions
    ): List<QuestionAnswerPairEntity> {
        val tags = answerDao.getTagsByQuestion(questionId = queryOptions.questionId)
        val pairsWithTags = answerDao.getQuestionAnswerPairsByCourseAndTags(
            courseCode = queryOptions.courseCode, tags = tags
        )

        val pairTags = pairsWithTags
            .groupBy { it.pairId }
            .mapValues { it.value.map { pairIdWithTag -> pairIdWithTag.tag } }
        val pairsIds = pairTags.keys.toList()

        return answerDao.getQuestionAnswerPairs(
            ids = pairsIds,
            difficulty = queryOptions.difficulty
        )
    }

}

fun answerLocalDataSourceFactory(appLocalDatabase: AppLocalDatabase): AnswerLocalDataSource {
    return AnswerLocalDataSourceImpl(
        answerDao = appLocalDatabase.answerDao(),
        updateTimeDao = appLocalDatabase.localUpdateTimeDao()
    )
}