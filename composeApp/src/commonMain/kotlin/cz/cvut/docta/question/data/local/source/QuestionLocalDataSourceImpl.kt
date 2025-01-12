package cz.cvut.docta.question.data.local.source

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.core.data.local.dao.LocalUpdateTimeDao
import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.core.data.model.TableName
import cz.cvut.docta.question.data.local.dao.QuestionDao
import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.data.local.model.entity.StepByStepLessonQuestionEntity
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.data.local.model.tag.QuestionIdWithTag
import cz.cvut.docta.question.data.local.model.tag.QuestionTagDefaultLessonAssociation
import cz.cvut.docta.question.data.local.model.tag.QuestionTagEntity
import cz.cvut.docta.question.data.local.model.tag.QuestionTagQuestionAssociation
import cz.cvut.docta.question.data.mapper.toAnswerOptionsQuestionEntities
import cz.cvut.docta.question.data.mapper.toFillInBlanksQuestionEntities
import cz.cvut.docta.question.data.mapper.toOpenAnswerQuestionEntities
import cz.cvut.docta.question.data.mapper.toQuestionAnswerPairsQuestionEntities
import cz.cvut.docta.question.data.mapper.toQuestionEntities
import cz.cvut.docta.question.data.mapper.toSealedAnswerOptionsQuestionDetails
import cz.cvut.docta.question.data.mapper.toSealedFillInBlanksQuestionDetails
import cz.cvut.docta.question.data.mapper.toSealedOpenAnswerQuestionDetails
import cz.cvut.docta.question.data.mapper.toSealedQuestionAnswerPairsQuestionDetails

class QuestionLocalDataSourceImpl(
    private val questionDao: QuestionDao,
    private val updateTimeDao: LocalUpdateTimeDao
) : QuestionLocalDataSource {

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

    override suspend fun synchroniseQuestionTags(
        tagsToSync: EntitiesToSynchronise<QuestionTagEntity>,
        courseCode: String,
        timestamp: Long
    ) {
        questionDao.deleteAndUpsertQuestionTags(
            toDelete = tagsToSync.toDelete, toUpsert = tagsToSync.toUpsert
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

    override suspend fun synchroniseQuestionTagDefaultLessonAssociations(
        associationsToSync: EntitiesToSynchronise<QuestionTagDefaultLessonAssociation>,
        courseCode: String,
        timestamp: Long
    ) {
        questionDao.deleteAndUpsertQuestionTagDefaultLessonAssociations(
            toDelete = associationsToSync.toDelete, toUpsert = associationsToSync.toUpsert
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

    override suspend fun synchroniseQuestionTagQuestionAssociations(
        associationsToSync: EntitiesToSynchronise<QuestionTagQuestionAssociation>,
        courseCode: String,
        timestamp: Long
    ) {
        questionDao.deleteAndUpsertQuestionTagQuestionAssociations(
            toDelete = associationsToSync.toDelete, toUpsert = associationsToSync.toUpsert
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

    override suspend fun synchroniseQuestions(
        questionsToSync: EntitiesToSynchronise<QuestionDetails>,
        courseCode: String,
        timestamp: Long
    ) {
        val questionsToDelete = questionsToSync.toDelete.toQuestionEntities()
        val questionsToUpsert = questionsToSync.toUpsert.toQuestionEntities()

        val openAnswerQuestionsToUpsert = questionsToSync.toUpsert.toOpenAnswerQuestionEntities()
        val fillInBlanksQuestionsToUpsert = questionsToSync.toUpsert.toFillInBlanksQuestionEntities()
        val answerOptionsQuestionsToUpsert = questionsToSync.toUpsert.toAnswerOptionsQuestionEntities()
        val questionAnswerPairsQuestionsToUpsert = questionsToSync.toUpsert.toQuestionAnswerPairsQuestionEntities()

        questionDao.deleteAndUpsertQuestionsAndInheritedQuestions(
            questionsToDelete = questionsToDelete,
            questionsToUpsert = questionsToUpsert,
            openAnswerQuestionsToUpsert = openAnswerQuestionsToUpsert,
            fillInBlanksQuestionsToUpsert = fillInBlanksQuestionsToUpsert,
            answerOptionsQuestionsToUpsert = answerOptionsQuestionsToUpsert,
            questionAnswerPairsQuestionsToUpsert = questionAnswerPairsQuestionsToUpsert
        )
    }

    override suspend fun getDefaultLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<QuestionDetails> {
        val tags = questionDao.getTagsOfLesson(lessonId = queryOptions.lessonId)
        val questionsIdsWithTags = questionDao.getQuestionsByCourseAndTags(
            courseCode = queryOptions.courseCode, tags = tags
        )

        val questionIds = getQuestionIdsByTags(
            tags = tags, questionsIdsWithTags = questionsIdsWithTags, queryOptions = queryOptions
        )

        return getDefaultLessonQuestionsByIds(
            ids = questionIds, difficulty = queryOptions.difficulty
        )
    }

    private fun getQuestionIdsByTags(
        tags: List<String>,
        questionsIdsWithTags: List<QuestionIdWithTag>,
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<Long> {
        val questionTags = questionsIdsWithTags
            .groupBy { it.questionId }
            .mapValues { it.value.map { questionIdWithTag -> questionIdWithTag.tag } }
            .let { questionIdToTagsMap ->
                if (queryOptions.matchAllTags) {
                    questionIdToTagsMap.filterValues { tags.containsAll(it) }
                } else {
                    questionIdToTagsMap
                }
            }
        return questionTags.keys.toList()
    }

    private suspend fun getDefaultLessonQuestionsByIds(
        ids: List<Long>,
        difficulty: String
    ): List<QuestionDetails> {
        val openAnswerQuestions = questionDao
            .getOpenAnswerQuestions(ids = ids, difficulty = difficulty)
            .toSealedOpenAnswerQuestionDetails()
        val fillInBlanksQuestions = questionDao
            .getFillInBlanksQuestions(ids = ids, difficulty = difficulty)
            .toSealedFillInBlanksQuestionDetails()
        val answerOptionsQuestions = questionDao
            .getAnswerOptionsQuestions(ids = ids, difficulty = difficulty)
            .toSealedAnswerOptionsQuestionDetails()
        val questionAnswerPairsQuestions = questionDao
            .getQuestionAnswerPairsQuestions(ids = ids, difficulty = difficulty)
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
            tableName = TableName.StepByStepQuestion.name,
            updateTime = timestamp,
            courseCode = courseCode
        )
    }

    override suspend fun synchroniseStepByStepLessonQuestions(
        questionsToSync: EntitiesToSynchronise<StepByStepLessonQuestionEntity>,
        courseCode: String,
        timestamp: Long
    ) {
        questionDao.deleteAndUpsertStepByStepLessonQuestions(
            toDelete = questionsToSync.toDelete, toUpsert = questionsToSync.toUpsert
        )
    }

    override suspend fun getStepByStepLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.StepByStep
    ): List<StepByStepLessonQuestionEntity> {
        return questionDao.getStepByStepLessonQuestions(lessonId = queryOptions.lessonId)
    }

}

fun questionLocalDataSourceFactory(appLocalDatabase: AppLocalDatabase): QuestionLocalDataSource {
    return QuestionLocalDataSourceImpl(
        questionDao = appLocalDatabase.questionDao(),
        updateTimeDao = appLocalDatabase.localUpdateTimeDao()
    )
}