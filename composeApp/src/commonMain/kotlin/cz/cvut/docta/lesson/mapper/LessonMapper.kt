package cz.cvut.docta.lesson.mapper

import cz.cvut.docta.core.utils.enumValueOrNull
import cz.cvut.docta.lesson.data.local.model.DefaultLessonType
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetails
import cz.cvut.docta.lesson.data.local.model.LessonDetailsStatistics
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetailsWithStatistics
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonDraft
import cz.cvut.docta.lesson.domain.model.LessonStatistics


fun LessonDetailsStatistics.toDomainLessonStatistics(): LessonStatistics {
    return LessonStatistics(
        isDone = isDone
    )
}


fun List<LessonDetailsWithStatistics>.toDomainLessons(): List<Lesson> {
    return mapNotNull { it.toDomainLesson() }
}

fun LessonDetailsWithStatistics.toDomainLesson(): Lesson? {
    val difficulty = enumValueOrNull<LessonDifficulty>(difficulty) ?: return null

    return when (this) {
        is LessonDetailsWithStatistics.DefaultLesson -> when (this.type) {
            DefaultLessonType.Default -> Lesson.Default(
                id = id,
                name = name,
                statistics = statistics.toDomainLessonStatistics(),
                difficulty = difficulty
            )
            DefaultLessonType.Test -> Lesson.Test(
                id = id,
                name = name,
                statistics = statistics.toDomainLessonStatistics()
            )
        }
        is LessonDetailsWithStatistics.StepByStepLesson -> Lesson.StepByStep(
            id = id,
            name = name,
            statistics = statistics.toDomainLessonStatistics(),
            difficulty = difficulty,
            description = description
        )
    }
}


fun List<LessonDetails>.toLessonDraftList(): List<LessonDraft> {
    return mapNotNull { it.toLessonDraft() }
}

fun LessonDetails.toLessonDraft(): LessonDraft? {
    val difficulty = enumValueOrNull<LessonDifficulty>(difficulty) ?: return null

    return when (this) {
        is LessonDetails.Default -> when (this.type) {
            DefaultLessonType.Default -> LessonDraft.Default(
                id = id,
                name = name,
                difficulty = difficulty
            )
            DefaultLessonType.Test -> LessonDraft.Test(
                id = id,
                name = name
            )
        }
        is LessonDetails.StepByStep -> LessonDraft.StepByStep(
            id = id,
            name = name,
            description = description,
            difficulty = difficulty
        )
    }
}
