package cz.cvut.docta.question.data.remote.source

import cz.cvut.docta.core.data.model.TableName
import cz.cvut.docta.core.data.remote.AppRemoteDatabase
import cz.cvut.docta.core.data.remote.dao.RemoteUpdateTimeDao
import cz.cvut.docta.question.data.remote.dao.QuestionRemoteDao
import cz.cvut.docta.question.data.remote.mapper.toSealedAnswerOptionsQuestionDetails
import cz.cvut.docta.question.data.remote.mapper.toSealedFillInBlanksQuestionDetails
import cz.cvut.docta.question.data.remote.mapper.toSealedOpenAnswerQuestionDetails
import cz.cvut.docta.question.data.remote.mapper.toSealedQuestionAnswerPairsQuestionDetails
import cz.cvut.docta.question.data.remote.model.QuestionRemoteDetails
import cz.cvut.docta.question.data.remote.model.entity.StepByStepLessonQuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagDefaultLessonRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagQuestionRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagRemoteEntity

class QuestionRemoteDataSourceImpl(
    private val questionDao: QuestionRemoteDao,
    private val updateTimeDao: RemoteUpdateTimeDao
) : QuestionRemoteDataSource {

    override suspend fun getQuestionTagUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.QuestionTag.name, courseCode = courseCode
        )
    }

    override suspend fun saveQuestionTagUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.QuestionTag.name, updateTime = timestamp, courseCode = courseCode
        )
    }

    override suspend fun getQuestionTagsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionTagRemoteEntity> {
        return questionDao.getQuestionTagsAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
        )
    }


    override suspend fun getQuestionTagDefaultLessonAssociationUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.QuestionTagDefaultLessonAssociation.name, courseCode = courseCode
        )
    }

    override suspend fun saveQuestionTagDefaultLessonAssociationUpdateTime(
        courseCode: String,
        timestamp: Long
    ) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.QuestionTagDefaultLessonAssociation.name,
            updateTime = timestamp,
            courseCode = courseCode
        )
    }

    override suspend fun getQuestionTagDefaultLessonAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionTagDefaultLessonRemoteAssociation> {
        return questionDao.getQuestionTagDefaultLessonAssociationsAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
        )
    }


    override suspend fun getQuestionTagQuestionAssociationUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.QuestionTagQuestionAssociation.name, courseCode = courseCode
        )
    }

    override suspend fun saveQuestionTagQuestionAssociationUpdateTime(
        courseCode: String,
        timestamp: Long
    ) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.QuestionTagQuestionAssociation.name,
            updateTime = timestamp,
            courseCode = courseCode
        )
    }

    override suspend fun getQuestionTagQuestionAssociationsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionTagQuestionRemoteAssociation> {
        return questionDao.getQuestionTagQuestionAssociationsAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
        )
    }


    override suspend fun getQuestionUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.Question.name, courseCode = courseCode
        )
    }

    override suspend fun saveQuestionUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.Question.name, updateTime = timestamp, courseCode = courseCode
        )
    }

    override suspend fun getQuestionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<QuestionRemoteDetails> {
        val openAnswerQuestions = questionDao
            .getOpenAnswerQuestionsAfterTimestamp(courseCode = courseCode, timestamp = timestamp)
            .toSealedOpenAnswerQuestionDetails()
        val fillInBlanksQuestions = questionDao
            .getFillInBlanksQuestionsAfterTimestamp(courseCode = courseCode, timestamp = timestamp)
            .toSealedFillInBlanksQuestionDetails()
        val answerOptionsQuestions = questionDao
            .getAnswerOptionsQuestionsAfterTimestamp(courseCode = courseCode, timestamp = timestamp)
            .toSealedAnswerOptionsQuestionDetails()
        val questionAnswerPairsQuestions = questionDao
            .getQuestionAnswerPairsQuestionsAfterTimestamp(courseCode = courseCode, timestamp = timestamp)
            .toSealedQuestionAnswerPairsQuestionDetails()

        return openAnswerQuestions + fillInBlanksQuestions + answerOptionsQuestions +
                questionAnswerPairsQuestions
    }


    override suspend fun getStepByStepQuestionUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(
            tableName = TableName.StepByStepQuestion.name, courseCode = courseCode
        )
    }

    override suspend fun saveStepByStepQuestionUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.StepByStepQuestion.name, updateTime = timestamp, courseCode = courseCode
        )
    }

    override suspend fun getStepByStepQuestionsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<StepByStepLessonQuestionRemoteEntity> {
        return questionDao.getStepByStepLessonQuestionsAfterTimestamp(
            courseCode = courseCode, timestamp = timestamp
        )
    }

}

fun questionRemoteDataSourceFactory(appRemoteDatabase: AppRemoteDatabase): QuestionRemoteDataSource {
    return QuestionRemoteDataSourceImpl(
        questionDao = appRemoteDatabase.questionRemoteDao(),
        updateTimeDao = appRemoteDatabase.remoteUpdateTimeDao()
    )
}