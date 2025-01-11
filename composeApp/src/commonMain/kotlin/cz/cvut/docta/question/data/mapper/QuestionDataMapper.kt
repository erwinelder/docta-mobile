package cz.cvut.docta.question.data.mapper

import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.question.data.local.model.entity.AnswerOptionsQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.FillInBlanksQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.OpenAnswerQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.QuestionAnswerPairsQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.QuestionEntity
import cz.cvut.docta.question.data.local.model.entity.StepByStepLessonQuestionEntity
import cz.cvut.docta.question.data.local.model.entity_with_details.AnswerOptionsQuestionWithDetails
import cz.cvut.docta.question.data.local.model.entity_with_details.FillInBlanksQuestionWithDetails
import cz.cvut.docta.question.data.local.model.entity_with_details.OpenAnswerQuestionWithDetails
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionAnswerPairsQuestionWithDetails
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.data.local.model.tag.QuestionTagDefaultLessonAssociation
import cz.cvut.docta.question.data.local.model.tag.QuestionTagEntity
import cz.cvut.docta.question.data.local.model.tag.QuestionTagQuestionAssociation
import cz.cvut.docta.question.data.remote.model.QuestionRemoteDetails
import cz.cvut.docta.question.data.remote.model.entity.StepByStepLessonQuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagDefaultLessonRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagQuestionRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagRemoteEntity


fun List<OpenAnswerQuestionWithDetails>.toSealedOpenAnswerQuestionDetails() =
    map { it.wrapInSealedClass() }

fun OpenAnswerQuestionWithDetails.wrapInSealedClass(): QuestionDetails.OpenAnswer {
    return QuestionDetails.OpenAnswer(
        id = id,
        difficulty = difficulty,
        text = text
    )
}


fun List<FillInBlanksQuestionWithDetails>.toSealedFillInBlanksQuestionDetails() =
    map { it.wrapInSealedClass() }

fun FillInBlanksQuestionWithDetails.wrapInSealedClass(): QuestionDetails.FillInBlanks {
    return QuestionDetails.FillInBlanks(
        id = id,
        difficulty = difficulty,
        text = text
    )
}


fun List<AnswerOptionsQuestionWithDetails>.toSealedAnswerOptionsQuestionDetails() =
    map { it.wrapInSealedClass() }

fun AnswerOptionsQuestionWithDetails.wrapInSealedClass(): QuestionDetails.AnswerOptions {
    return QuestionDetails.AnswerOptions(
        id = id,
        difficulty = difficulty,
        text = text,
        correctOptionId = correctOptionId
    )
}


fun List<QuestionAnswerPairsQuestionWithDetails>.toSealedQuestionAnswerPairsQuestionDetails() =
    map { it.wrapInSealedClass() }

fun QuestionAnswerPairsQuestionWithDetails.wrapInSealedClass(): QuestionDetails.QuestionAnswerPairs {
    return QuestionDetails.QuestionAnswerPairs(
        id = id,
        difficulty = difficulty
    )
}


fun List<QuestionDetails>.toQuestionEntities() = map { it.toQuestionEntity() }

fun QuestionDetails.toQuestionEntity(): QuestionEntity {
    return QuestionEntity(id = id, difficulty = difficulty)
}


fun List<QuestionDetails>.toOpenAnswerQuestionEntities() = mapNotNull { it.toOpenAnswerQuestionEntity() }

fun QuestionDetails.toOpenAnswerQuestionEntity(): OpenAnswerQuestionEntity? {
    if (this !is QuestionDetails.OpenAnswer) return null

    return OpenAnswerQuestionEntity(questionId = id, text = text)
}


fun List<QuestionDetails>.toFillInBlanksQuestionEntities() = mapNotNull { it.toFillInBlanksQuestionEntity() }

fun QuestionDetails.toFillInBlanksQuestionEntity(): FillInBlanksQuestionEntity? {
    if (this !is QuestionDetails.FillInBlanks) return null

    return FillInBlanksQuestionEntity(questionId = id, text = text)
}


fun List<QuestionDetails>.toAnswerOptionsQuestionEntities() = mapNotNull { it.toAnswerOptionsQuestionEntity() }

fun QuestionDetails.toAnswerOptionsQuestionEntity(): AnswerOptionsQuestionEntity? {
    if (this !is QuestionDetails.AnswerOptions) return null

    return AnswerOptionsQuestionEntity(
        questionId = id, text = text, correctOptionId = correctOptionId
    )
}


fun List<QuestionDetails>.toQuestionAnswerPairsQuestionEntities() = mapNotNull { it.toQuestionAnswerPairsQuestionEntity() }

fun QuestionDetails.toQuestionAnswerPairsQuestionEntity(): QuestionAnswerPairsQuestionEntity? {
    if (this !is QuestionDetails.QuestionAnswerPairs) return null

    return QuestionAnswerPairsQuestionEntity(questionId = id)
}


fun List<QuestionTagRemoteEntity>.toQuestionTagsToSync(): EntitiesToSynchronise<QuestionTagEntity> {
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = QuestionTagRemoteEntity::toQuestionTagEntity
    )
}

fun QuestionTagRemoteEntity.toQuestionTagEntity(): QuestionTagEntity {
    return QuestionTagEntity(courseCode = courseCode, tag = tag)
}


fun List<QuestionTagDefaultLessonRemoteAssociation>.toQuestionTagDefaultLessonAssociationsToSync():
        EntitiesToSynchronise<QuestionTagDefaultLessonAssociation>
{
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = QuestionTagDefaultLessonRemoteAssociation::toQuestionTagDefaultLessonAssociation
    )
}

fun QuestionTagDefaultLessonRemoteAssociation.toQuestionTagDefaultLessonAssociation(): QuestionTagDefaultLessonAssociation {
    return QuestionTagDefaultLessonAssociation(
        courseCode = courseCode, tag = tag, lessonId = lessonId
    )
}


fun List<QuestionTagQuestionRemoteAssociation>.toQuestionTagQuestionAssociationsToSync():
        EntitiesToSynchronise<QuestionTagQuestionAssociation>
{
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = QuestionTagQuestionRemoteAssociation::toQuestionTagQuestionAssociation
    )
}

fun QuestionTagQuestionRemoteAssociation.toQuestionTagQuestionAssociation(): QuestionTagQuestionAssociation {
    return QuestionTagQuestionAssociation(
        courseCode = courseCode, tag = tag, questionId = questionId
    )
}


fun List<QuestionRemoteDetails>.toQuestionDetailsToSync(): EntitiesToSynchronise<QuestionDetails> {
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = QuestionRemoteDetails::toQuestionDetails
    )
}

fun QuestionRemoteDetails.toQuestionDetails(): QuestionDetails {
    return when (this) {
        is QuestionRemoteDetails.OpenAnswer -> QuestionDetails.OpenAnswer(
            id = id, difficulty = difficulty, text = text
        )
        is QuestionRemoteDetails.FillInBlanks -> QuestionDetails.FillInBlanks(
            id = id, difficulty = difficulty, text = text
        )
        is QuestionRemoteDetails.AnswerOptions -> QuestionDetails.AnswerOptions(
            id = id, difficulty = difficulty, text = text, correctOptionId = correctOptionId
        )
        is QuestionRemoteDetails.QuestionAnswerPairs -> QuestionDetails.QuestionAnswerPairs(
            id = id, difficulty = difficulty
        )
    }
}


fun List<StepByStepLessonQuestionRemoteEntity>.toStepByStepLessonQuestionEntitiesToSync():
        EntitiesToSynchronise<StepByStepLessonQuestionEntity>
{
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = StepByStepLessonQuestionRemoteEntity::toStepByStepLessonQuestionEntity
    )
}

fun StepByStepLessonQuestionRemoteEntity.toStepByStepLessonQuestionEntity(): StepByStepLessonQuestionEntity {
    return StepByStepLessonQuestionEntity(
        lessonId = lessonId,
        id = id,
        orderNum = orderNum,
        questionText = questionText,
        correctAnswerText = correctAnswerText
    )
}
