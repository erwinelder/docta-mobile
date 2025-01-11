package cz.cvut.docta.answer.data.remote.source

import cz.cvut.docta.answer.data.remote.dao.AnswerRemoteDao
import cz.cvut.docta.answer.data.remote.model.AnswerOptionRemoteEntity
import cz.cvut.docta.answer.data.remote.model.BlankAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.CorrectOpenAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.PairTagPairRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.PairTagQuestionRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairRemoteEntity
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairTagRemoteEntity
import cz.cvut.docta.core.data.model.TableName
import cz.cvut.docta.core.data.remote.AppRemoteDatabase
import cz.cvut.docta.core.data.remote.dao.RemoteUpdateTimeDao
import cz.cvut.docta.core.data.remote.model.RemoteUpdateTime

class AnswerRemoteDataSourceImpl(
    private val answerDao: AnswerRemoteDao,
    private val updateTimeDao: RemoteUpdateTimeDao
) : AnswerRemoteDataSource {

    override suspend fun getCorrectOpenAnswerUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.CorrectOpenAnswer.name, courseCode = courseCode
        )
    }

    override suspend fun saveCorrectOpenAnswerUpdateTime(courseCode: String, timestamp: Long) {
        val updateTime = RemoteUpdateTime(
            tableName = TableName.CorrectOpenAnswer.name,
            courseCode = courseCode,
            updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun getCorrectOpenAnswersAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<CorrectOpenAnswerRemoteEntity> {
        return answerDao.getCorrectOpenAnswersAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
        )
    }


    override suspend fun getBlankAnswerUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.BlankAnswer.name, courseCode = courseCode
        )
    }

    override suspend fun saveBlankAnswerUpdateTime(courseCode: String, timestamp: Long) {
        val updateTime = RemoteUpdateTime(
            tableName = TableName.BlankAnswer.name,
            courseCode = courseCode,
            updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun getBlankAnswersAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<BlankAnswerRemoteEntity> {
        return answerDao.getBlanksAnswersAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
        )
    }


    override suspend fun getAnswerOptionUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.AnswerOption.name, courseCode = courseCode
        )
    }

    override suspend fun saveAnswerOptionUpdateTime(courseCode: String, timestamp: Long) {
        val updateTime = RemoteUpdateTime(
            tableName = TableName.AnswerOption.name,
            courseCode = courseCode,
            updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun getAnswerOptionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<AnswerOptionRemoteEntity> {
        return answerDao.getAnswerOptionsAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
        )
    }


    override suspend fun getPairTagUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.QuestionAnswerPairTag.name, courseCode = courseCode
        )
    }

    override suspend fun savePairTagUpdateTime(courseCode: String, timestamp: Long) {
        val updateTime = RemoteUpdateTime(
            tableName = TableName.QuestionAnswerPairTag.name,
            courseCode = courseCode,
            updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun getPairTagsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionAnswerPairTagRemoteEntity> {
        return answerDao.getPairTagsAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
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
        val updateTime = RemoteUpdateTime(
            tableName = TableName.PairTagQuestionAssociation.name,
            courseCode = courseCode,
            updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun getPairTagQuestionAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<PairTagQuestionRemoteAssociation> {
        return answerDao.getPairTagQuestionAssociationsAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
        )
    }


    override suspend fun getPairTagPairAssociationUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.PairTagPairAssociation.name, courseCode = courseCode
        )
    }

    override suspend fun savePairTagPairAssociationUpdateTime(courseCode: String, timestamp: Long) {
        val updateTime = RemoteUpdateTime(
            tableName = TableName.PairTagPairAssociation.name,
            courseCode = courseCode,
            updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun getPairTagPairAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<PairTagPairRemoteAssociation> {
        return answerDao.getPairTagPairAssociationsAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
        )
    }


    override suspend fun getQuestionAnswerPairUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.QuestionAnswerPair.name, courseCode = courseCode
        )
    }

    override suspend fun saveQuestionAnswerPairUpdateTime(courseCode: String, timestamp: Long) {
        val updateTime = RemoteUpdateTime(
            tableName = TableName.QuestionAnswerPair.name,
            courseCode = courseCode,
            updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun getQuestionAnswerPairsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionAnswerPairRemoteEntity> {
        return answerDao.getQuestionAnswerPairsAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
        )
    }

}

fun answerRemoteDataSourceFactory(appRemoteDatabase: AppRemoteDatabase): AnswerRemoteDataSource {
    return AnswerRemoteDataSourceImpl(
        answerDao = appRemoteDatabase.answerRemoteDao(),
        updateTimeDao = appRemoteDatabase.remoteUpdateTimeDao()
    )
}