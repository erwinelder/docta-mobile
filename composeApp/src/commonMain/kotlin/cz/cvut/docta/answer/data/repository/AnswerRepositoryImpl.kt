package cz.cvut.docta.answer.data.repository

import cz.cvut.docta.answer.data.local.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.local.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.local.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.local.source.AnswerLocalDataSource
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairsQueryOptions
import cz.cvut.docta.answer.data.mapper.toAnswerOptionsToSync
import cz.cvut.docta.answer.data.mapper.toBlankAnswersToSync
import cz.cvut.docta.answer.data.mapper.toCorrectOpenAnswersToSync
import cz.cvut.docta.answer.data.mapper.toPairTagPairRemoteAssociationsToSync
import cz.cvut.docta.answer.data.mapper.toPairTagQuestionRemoteAssociationsToSync
import cz.cvut.docta.answer.data.mapper.toQuestionAnswerPairTagsToSync
import cz.cvut.docta.answer.data.mapper.toQuestionAnswerPairsToSync
import cz.cvut.docta.answer.data.remote.model.AnswerOptionRemoteEntity
import cz.cvut.docta.answer.data.remote.model.BlankAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.CorrectOpenAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.PairTagPairRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.PairTagQuestionRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairRemoteEntity
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairTagRemoteEntity
import cz.cvut.docta.answer.data.remote.source.AnswerRemoteDataSource
import cz.cvut.docta.core.data.utils.synchroniseData

class AnswerRepositoryImpl(
    private val localSource: AnswerLocalDataSource,
    private val remoteSource: AnswerRemoteDataSource
) : AnswerRepository {

    private suspend fun synchroniseOpenAnswers(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getCorrectOpenAnswerUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getCorrectOpenAnswerUpdateTime,
            remoteDataGetter = remoteSource::getCorrectOpenAnswersAfterTimestamp,
            remoteDataToDataToSyncMapper = List<CorrectOpenAnswerRemoteEntity>::toCorrectOpenAnswersToSync,
            localSynchroniser = localSource::synchroniseOpenAnswers
        )
    }

    override suspend fun getOpenAnswers(
        courseCode: String,
        questionId: Long
    ): List<CorrectOpenAnswerEntity> {
        synchroniseOpenAnswers(courseCode = courseCode)
        return localSource.getOpenAnswers(questionId = questionId)
    }


    private suspend fun synchroniseBlanksAnswers(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getBlankAnswerUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getBlankAnswerUpdateTime,
            remoteDataGetter = remoteSource::getBlankAnswersAfterTimestamp,
            remoteDataToDataToSyncMapper = List<BlankAnswerRemoteEntity>::toBlankAnswersToSync,
            localSynchroniser = localSource::synchroniseBlanksAnswers
        )
    }

    override suspend fun getBlanksAnswers(
        courseCode: String,
        questionId: Long
    ): List<BlankAnswerEntity> {
        synchroniseBlanksAnswers(courseCode = courseCode)
        return localSource.getBlanksAnswers(questionId = questionId)
    }


    private suspend fun synchroniseAnswerOptions(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getAnswerOptionUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getAnswerOptionUpdateTime,
            remoteDataGetter = remoteSource::getAnswerOptionsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<AnswerOptionRemoteEntity>::toAnswerOptionsToSync,
            localSynchroniser = localSource::synchroniseAnswerOptions
        )
    }

    override suspend fun getAnswerOptions(
        courseCode: String,
        questionId: Long
    ): List<AnswerOptionEntity> {
        synchroniseAnswerOptions(courseCode = courseCode)
        return localSource.getAnswerOptions(questionId = questionId)
    }


    private suspend fun synchroniseQuestionAnswerPairsInfrastructure(courseCode: String) {
        synchronisePairTags(courseCode = courseCode)
        synchroniseQuestionAnswerPairs(courseCode = courseCode)
        synchronisePairTagQuestionAssociations(courseCode = courseCode)
        synchronisePairTagPairAssociations(courseCode = courseCode)
    }

    private suspend fun synchronisePairTags(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getPairTagUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getPairTagUpdateTime,
            remoteDataGetter = remoteSource::getPairTagsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<QuestionAnswerPairTagRemoteEntity>::toQuestionAnswerPairTagsToSync,
            localSynchroniser = localSource::synchronisePairTags
        )
    }

    private suspend fun synchronisePairTagQuestionAssociations(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getPairTagQuestionAssociationUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getPairTagQuestionAssociationUpdateTime,
            remoteDataGetter = remoteSource::getPairTagQuestionAssociationsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<PairTagQuestionRemoteAssociation>::toPairTagQuestionRemoteAssociationsToSync,
            localSynchroniser = localSource::synchronisePairTagQuestionAssociations
        )
    }

    private suspend fun synchronisePairTagPairAssociations(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getPairTagPairAssociationUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getPairTagPairAssociationUpdateTime,
            remoteDataGetter = remoteSource::getPairTagPairAssociationsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<PairTagPairRemoteAssociation>::toPairTagPairRemoteAssociationsToSync,
            localSynchroniser = localSource::synchronisePairTagPairAssociations
        )
    }

    private suspend fun synchroniseQuestionAnswerPairs(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getQuestionAnswerPairUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getQuestionAnswerPairUpdateTime,
            remoteDataGetter = remoteSource::getQuestionAnswerPairsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<QuestionAnswerPairRemoteEntity>::toQuestionAnswerPairsToSync,
            localSynchroniser = localSource::synchroniseQuestionAnswerPairs
        )
    }

    override suspend fun getQuestionAnswerPairs(
        queryOptions: QuestionAnswerPairsQueryOptions
    ): List<QuestionAnswerPairEntity> {
        synchroniseQuestionAnswerPairsInfrastructure(courseCode = queryOptions.courseCode)
        return localSource.getQuestionAnswerPairs(queryOptions = queryOptions)
    }

}