package cz.cvut.docta.question.data.repository

import cz.cvut.docta.core.data.utils.synchroniseData
import cz.cvut.docta.question.data.local.source.QuestionLocalDataSource
import cz.cvut.docta.question.data.mapper.toQuestionDetailsToSync
import cz.cvut.docta.question.data.mapper.toQuestionTagDefaultLessonAssociationsToSync
import cz.cvut.docta.question.data.mapper.toQuestionTagQuestionAssociationsToSync
import cz.cvut.docta.question.data.mapper.toQuestionTagsToSync
import cz.cvut.docta.question.data.mapper.toStepByStepLessonQuestionEntitiesToSync
import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.data.local.model.entity.StepByStepLessonQuestionEntity
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.data.remote.model.QuestionRemoteDetails
import cz.cvut.docta.question.data.remote.model.entity.StepByStepLessonQuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagDefaultLessonRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagQuestionRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagRemoteEntity
import cz.cvut.docta.question.data.remote.source.QuestionRemoteDataSource

class QuestionRepositoryImpl(
    private val localSource: QuestionLocalDataSource,
    private val remoteSource: QuestionRemoteDataSource
) : QuestionRepository {

    private suspend fun synchroniseQuestionsInfrastructure(courseCode: String) {
        synchroniseQuestionTags(courseCode = courseCode)
        synchroniseQuestions(courseCode = courseCode)
        synchroniseQuestionTagDefaultLessonAssociations(courseCode = courseCode)
        synchroniseQuestionTagQuestionAssociations(courseCode = courseCode)
    }

    private suspend fun synchroniseQuestionTags(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getQuestionTagUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getQuestionTagUpdateTime,
            remoteDataGetter = remoteSource::getQuestionTagsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<QuestionTagRemoteEntity>::toQuestionTagsToSync,
            localSynchroniser = localSource::synchroniseQuestionTags
        )
    }

    private suspend fun synchroniseQuestionTagDefaultLessonAssociations(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getQuestionTagDefaultLessonAssociationUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getQuestionTagDefaultLessonAssociationUpdateTime,
            remoteDataGetter = remoteSource::getQuestionTagDefaultLessonAssociationsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<QuestionTagDefaultLessonRemoteAssociation>::toQuestionTagDefaultLessonAssociationsToSync,
            localSynchroniser = localSource::synchroniseQuestionTagDefaultLessonAssociations
        )
    }

    private suspend fun synchroniseQuestionTagQuestionAssociations(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getQuestionTagQuestionAssociationUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getQuestionTagQuestionAssociationUpdateTime,
            remoteDataGetter = remoteSource::getQuestionTagQuestionAssociationsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<QuestionTagQuestionRemoteAssociation>::toQuestionTagQuestionAssociationsToSync,
            localSynchroniser = localSource::synchroniseQuestionTagQuestionAssociations
        )
    }

    private suspend fun synchroniseQuestions(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getQuestionUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getQuestionUpdateTime,
            remoteDataGetter = remoteSource::getQuestionsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<QuestionRemoteDetails>::toQuestionDetailsToSync,
            localSynchroniser = localSource::synchroniseQuestions
        )
    }

    override suspend fun getDefaultLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<QuestionDetails> {
        synchroniseQuestionsInfrastructure(courseCode = queryOptions.courseCode)
        return localSource.getDefaultLessonQuestions(queryOptions = queryOptions)
    }


    private suspend fun synchroniseStepByStepLessonQuestions(courseCode: String) {
        synchroniseData(
            courseCode = courseCode,
            localUpdateTimeGetter = localSource::getStepByStepQuestionUpdateTime,
            remoteUpdateTimeGetter = remoteSource::getStepByStepQuestionUpdateTime,
            remoteDataGetter = remoteSource::getStepByStepQuestionsAfterTimestamp,
            remoteDataToDataToSyncMapper = List<StepByStepLessonQuestionRemoteEntity>::toStepByStepLessonQuestionEntitiesToSync,
            localSynchroniser = localSource::synchroniseStepByStepLessonQuestions
        )
    }

    override suspend fun getStepByStepLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.StepByStep
    ): List<StepByStepLessonQuestionEntity> {
        synchroniseStepByStepLessonQuestions(courseCode = queryOptions.courseCode)
        return localSource.getStepByStepLessonQuestions(queryOptions = queryOptions)
    }

}