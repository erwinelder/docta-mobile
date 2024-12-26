package cz.cvut.docta.lesson.mapper

import cz.cvut.docta.lesson.data.model.LessonDetails
import cz.cvut.docta.lesson.data.model.LessonDetailsStatistics
import cz.cvut.docta.lesson.data.model.LessonDetailsWithStatistics
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonDraft
import cz.cvut.docta.lesson.domain.model.LessonStatistics
import cz.cvut.docta.lesson.domain.model.LessonType


fun LessonDetailsStatistics.toLessonStatistics(): LessonStatistics {
    return LessonStatistics(
        isDone = isDone
    )
}


fun List<LessonDetailsWithStatistics>.toLessonList(): List<Lesson> {
    return mapNotNull { it.toLesson() }
}

fun LessonDetailsWithStatistics.toLesson(): Lesson? {
    val difficulty = LessonDifficulty.entries.find { it.name == difficulty } ?: return null

    return when (this) {
        is LessonDetailsWithStatistics.DefaultLesson -> when (this.type) {
            LessonType.Default.name -> Lesson.Default(
                id = id,
                name = name,
                statistics = statistics.toLessonStatistics(),
                difficulty = difficulty
            )
            LessonType.Test.name -> Lesson.Test(
                id = id,
                name = name,
                statistics = statistics.toLessonStatistics()
            )
            else -> null
        }
        is LessonDetailsWithStatistics.StepByStepLesson -> Lesson.StepByStep(
            id = id,
            name = name,
            statistics = statistics.toLessonStatistics(),
            difficulty = difficulty,
            description = description
        )
    }
}


fun List<LessonDetails>.toLessonDraftList(): List<LessonDraft> {
    return mapNotNull { it.toLessonDraft() }
}

fun LessonDetails.toLessonDraft(): LessonDraft? {
    val difficulty = LessonDifficulty.entries.find { it.name == difficulty } ?: return null

    return when (this) {
        is LessonDetails.Default -> when (this.type) {
            LessonType.Default.name -> LessonDraft.Default(
                id = id,
                name = name,
                difficulty = difficulty
            )
            LessonType.Test.name -> LessonDraft.Test(
                id = id,
                name = name
            )
            else -> null
        }
        is LessonDetails.StepByStep -> LessonDraft.StepByStep(
            id = id,
            name = name,
            description = description,
            difficulty = difficulty
        )
    }
}
