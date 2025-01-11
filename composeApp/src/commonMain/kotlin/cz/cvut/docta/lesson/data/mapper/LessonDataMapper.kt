package cz.cvut.docta.lesson.data.mapper

import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.core.utils.enumValueOrNull
import cz.cvut.docta.lesson.data.local.model.DefaultLessonType
import cz.cvut.docta.lesson.data.local.model.entity.DefaultLessonEntity
import cz.cvut.docta.lesson.data.local.model.entity.LessonEntity
import cz.cvut.docta.lesson.data.local.model.entity.StepByStepLessonEntity
import cz.cvut.docta.lesson.data.local.model.entity_with_details.DefaultLessonWithDetails
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetails
import cz.cvut.docta.lesson.data.local.model.entity_with_details.StepByStepLessonWithDetails
import cz.cvut.docta.lesson.data.remote.model.LessonRemoteDetails


fun List<DefaultLessonWithDetails>.toSealedDefaultLessonDetails() =
    mapNotNull { it.wrapInSealedClass() }

fun DefaultLessonWithDetails.wrapInSealedClass(): LessonDetails.Default? {
    val type = enumValueOrNull<DefaultLessonType>(defaultLessonType) ?: return null

    return LessonDetails.Default(
        sectionId = sectionId,
        id = id,
        orderNum = orderNum,
        name = name,
        difficulty = difficulty,
        type = type,
        matchAllTags = matchAllTags
    )
}


fun List<StepByStepLessonWithDetails>.toSealedStepByStepLessonDetails() =
    map { it.wrapInSealedClass() }

fun StepByStepLessonWithDetails.wrapInSealedClass(): LessonDetails.StepByStep {
    return LessonDetails.StepByStep(
        sectionId = sectionId,
        id = id,
        orderNum = orderNum,
        name = name,
        difficulty = difficulty,
        description = description
    )
}


fun List<LessonDetails>.toLessonEntities() = map { it.toLessonEntity() }

fun LessonDetails.toLessonEntity(): LessonEntity {
    val type = when (this) {
        is LessonDetails.Default -> cz.cvut.docta.lesson.data.local.model.LessonType.Default
        is LessonDetails.StepByStep -> cz.cvut.docta.lesson.data.local.model.LessonType.StepByStep
    }

    return LessonEntity(
        sectionId = sectionId,
        id = id,
        type = type.name,
        orderNum = orderNum,
        name = name,
        difficulty = difficulty
    )
}


fun List<LessonDetails>.toDefaultLessonEntities() = mapNotNull { it.toDefaultLessonEntity() }

fun LessonDetails.toDefaultLessonEntity(): DefaultLessonEntity? {
    if (this !is LessonDetails.Default) return null

    return DefaultLessonEntity(
        lessonId = id,
        defaultLessonType = this.type.name,
        matchAllTags = matchAllTags
    )
}


fun List<LessonDetails>.toStepByStepLessonEntities() = mapNotNull { it.toStepByStepLessonEntity() }

fun LessonDetails.toStepByStepLessonEntity(): StepByStepLessonEntity? {
    if (this !is LessonDetails.StepByStep) return null

    return StepByStepLessonEntity(
        lessonId = id,
        description = description
    )
}


fun List<LessonRemoteDetails>.toLessonDetailsToSync(): EntitiesToSynchronise<LessonDetails> {
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = LessonRemoteDetails::toLessonDetails
    )
}

fun LessonRemoteDetails.toLessonDetails(): LessonDetails {
    return when (this) {
        is LessonRemoteDetails.Default -> LessonDetails.Default(
            sectionId = sectionId,
            id = id,
            orderNum = orderNum,
            name = name,
            difficulty = difficulty,
            type = type,
            matchAllTags = matchAllTags
        )
        is LessonRemoteDetails.StepByStep -> LessonDetails.StepByStep(
            sectionId = sectionId,
            id = id,
            orderNum = orderNum,
            name = name,
            difficulty = difficulty,
            description = description
        )
    }
}
