package cz.cvut.docta.lesson.data.remote.mapper

import cz.cvut.docta.core.utils.enumValueOrNull
import cz.cvut.docta.lesson.data.local.model.DefaultLessonType
import cz.cvut.docta.lesson.data.local.model.LessonDifficulty
import cz.cvut.docta.lesson.data.remote.model.LessonRemoteDetails
import cz.cvut.docta.lesson.data.remote.model.entity_with_details.DefaultLessonRemoteWithDetails
import cz.cvut.docta.lesson.data.remote.model.entity_with_details.StepByStepLessonRemoteWithDetails


fun List<DefaultLessonRemoteWithDetails>.toDefaultLessonDetailsList() = mapNotNull { it.toLessonDetails() }

fun DefaultLessonRemoteWithDetails.toLessonDetails(): LessonRemoteDetails.Default? {
    val type = enumValueOrNull<DefaultLessonType>(defaultLessonType) ?: return null
    val difficulty = enumValueOrNull<LessonDifficulty>(difficulty) ?: return null

    return LessonRemoteDetails.Default(
        updateTime = updateTime,
        deleted = deleted,
        courseCode = courseCode,
        sectionId = sectionId,
        id = id,
        orderNum = orderNum,
        name = name,
        difficulty = difficulty,
        type = type,
        matchAllTags = matchAllTags
    )
}


fun List<StepByStepLessonRemoteWithDetails>.toStepByStepLessonDetailsList() =
    mapNotNull { it.toLessonDetails() }

fun StepByStepLessonRemoteWithDetails.toLessonDetails(): LessonRemoteDetails.StepByStep? {
    val difficulty = enumValueOrNull<LessonDifficulty>(difficulty) ?: return null

    return LessonRemoteDetails.StepByStep(
        updateTime = updateTime,
        deleted = deleted,
        courseCode = courseCode,
        sectionId = sectionId,
        id = id,
        orderNum = orderNum,
        name = name,
        difficulty = difficulty,
        description = description
    )
}