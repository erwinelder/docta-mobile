package cz.cvut.docta.lesson.data.mapper

import cz.cvut.docta.lesson.data.model.DefaultLessonWithDetails
import cz.cvut.docta.lesson.data.model.LessonDetails
import cz.cvut.docta.lesson.data.model.StepByStepLessonWithDetails


fun List<DefaultLessonWithDetails>.toDefaultLessonDetailsList() = map { it.toLessonDetails() }

fun DefaultLessonWithDetails.toLessonDetails(): LessonDetails.Default {
    return LessonDetails.Default(
        sectionId = sectionId,
        id = id,
        orderNum = orderNum,
        name = name,
        difficulty = difficulty,
        type = defaultLessonType,
        matchAllTags = matchAllTags
    )
}


fun List<StepByStepLessonWithDetails>.toStepByStepLessonDetailsList() = map { it.toLessonDetails() }

fun StepByStepLessonWithDetails.toLessonDetails(): LessonDetails.StepByStep {
    return LessonDetails.StepByStep(
        sectionId = sectionId,
        id = id,
        orderNum = orderNum,
        name = name,
        difficulty = difficulty,
        description = description
    )
}
