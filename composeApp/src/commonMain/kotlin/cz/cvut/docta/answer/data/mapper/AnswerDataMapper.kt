package cz.cvut.docta.answer.data.mapper

import cz.cvut.docta.answer.data.local.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.local.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.local.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.local.model.PairTagPairAssociation
import cz.cvut.docta.answer.data.local.model.PairTagQuestionAssociation
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairTagEntity
import cz.cvut.docta.answer.data.remote.model.AnswerOptionRemoteEntity
import cz.cvut.docta.answer.data.remote.model.BlankAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.CorrectOpenAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.PairTagPairRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.PairTagQuestionRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairRemoteEntity
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairTagRemoteEntity
import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise


fun List<CorrectOpenAnswerRemoteEntity>.toCorrectOpenAnswersToSync(): EntitiesToSynchronise<CorrectOpenAnswerEntity> {
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = CorrectOpenAnswerRemoteEntity::toCorrectOpenAnswerEntity
    )
}

fun CorrectOpenAnswerRemoteEntity.toCorrectOpenAnswerEntity(): CorrectOpenAnswerEntity {
    return CorrectOpenAnswerEntity(questionId = questionId, id = id, text = text)
}


fun List<BlankAnswerRemoteEntity>.toBlankAnswersToSync(): EntitiesToSynchronise<BlankAnswerEntity> {
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = BlankAnswerRemoteEntity::toBlankAnswerEntity
    )
}

fun BlankAnswerRemoteEntity.toBlankAnswerEntity(): BlankAnswerEntity {
    return BlankAnswerEntity(questionId = questionId, blankNum = blankNum, id = id, text = text)
}


fun List<AnswerOptionRemoteEntity>.toAnswerOptionsToSync(): EntitiesToSynchronise<AnswerOptionEntity> {
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = AnswerOptionRemoteEntity::toAnswerOptionEntity
    )
}

fun AnswerOptionRemoteEntity.toAnswerOptionEntity(): AnswerOptionEntity {
    return AnswerOptionEntity(questionId = questionId, id = id, text = text)
}


fun List<QuestionAnswerPairTagRemoteEntity>.toQuestionAnswerPairTagsToSync():
        EntitiesToSynchronise<QuestionAnswerPairTagEntity>
{
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = QuestionAnswerPairTagRemoteEntity::toQuestionAnswerPairTagEntity
    )
}

fun QuestionAnswerPairTagRemoteEntity.toQuestionAnswerPairTagEntity(): QuestionAnswerPairTagEntity {
    return QuestionAnswerPairTagEntity(courseCode = courseCode, tag = tag)
}


fun List<PairTagQuestionRemoteAssociation>.toPairTagQuestionRemoteAssociationsToSync():
        EntitiesToSynchronise<PairTagQuestionAssociation>
{
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = PairTagQuestionRemoteAssociation::toPairTagQuestionAssociation
    )
}

fun PairTagQuestionRemoteAssociation.toPairTagQuestionAssociation(): PairTagQuestionAssociation {
    return PairTagQuestionAssociation(courseCode = courseCode, tag = tag, questionId = questionId)
}


fun List<PairTagPairRemoteAssociation>.toPairTagPairRemoteAssociationsToSync():
        EntitiesToSynchronise<PairTagPairAssociation>
{
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = PairTagPairRemoteAssociation::toPairTagQuestionAssociation
    )
}

fun PairTagPairRemoteAssociation.toPairTagQuestionAssociation(): PairTagPairAssociation {
    return PairTagPairAssociation(courseCode = courseCode, tag = tag, pairId = pairId)
}


fun List<QuestionAnswerPairRemoteEntity>.toQuestionAnswerPairsToSync():
        EntitiesToSynchronise<QuestionAnswerPairEntity>
{
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = QuestionAnswerPairRemoteEntity::toQuestionAnswerPairEntity
    )
}

fun QuestionAnswerPairRemoteEntity.toQuestionAnswerPairEntity(): QuestionAnswerPairEntity {
    return QuestionAnswerPairEntity(
        id = id, difficulty = difficulty, questionText = questionText, answerText = answerText
    )
}
