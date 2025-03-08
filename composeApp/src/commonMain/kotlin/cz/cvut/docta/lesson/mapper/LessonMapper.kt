package cz.cvut.docta.lesson.mapper

import cz.cvut.docta.lesson.data.local.model.DefaultLessonType
import cz.cvut.docta.lesson.data.local.model.LessonDifficulty
import cz.cvut.docta.lesson.data.local.model.UserLessonDetailsStats
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetails
import cz.cvut.docta.lesson.data.local.model.entity_with_details.LessonDetailsWithUserStats
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDraft
import cz.cvut.docta.lesson.domain.model.UserLessonStats


fun LessonDifficulty.toLessonDifficultyDomainModel(): cz.cvut.docta.lesson.domain.model.LessonDifficulty {
    return when(this) {
        LessonDifficulty.Easy -> cz.cvut.docta.lesson.domain.model.LessonDifficulty.Easy
        LessonDifficulty.Medium -> cz.cvut.docta.lesson.domain.model.LessonDifficulty.Medium
        LessonDifficulty.Hard -> cz.cvut.docta.lesson.domain.model.LessonDifficulty.Hard
    }
}


fun UserLessonDetailsStats.toDomainLessonStatistics(): UserLessonStats {
    return UserLessonStats(
        isDone = isDone
    )
}


fun List<LessonDetailsWithUserStats>.toDomainLessons(): List<Lesson> {
    return mapNotNull { it.toDomainLesson() }
}

fun LessonDetailsWithUserStats.toDomainLesson(): Lesson? {
    return when (this) {
        is LessonDetailsWithUserStats.DefaultLesson -> when (this.type) {
            DefaultLessonType.Default -> Lesson.Default(
                id = id,
                name = name,
                statistics = statistics.toDomainLessonStatistics(),
                difficulty = difficulty.toLessonDifficultyDomainModel()
            )
            DefaultLessonType.Test -> Lesson.Test(
                id = id,
                name = name,
                statistics = statistics.toDomainLessonStatistics()
            )
        }
        is LessonDetailsWithUserStats.StepByStepLesson -> Lesson.StepByStep(
            id = id,
            name = name,
            statistics = statistics.toDomainLessonStatistics(),
            difficulty = difficulty.toLessonDifficultyDomainModel(),
            description = description
        )
    }
}


fun List<LessonDetails>.toLessonDraftList(): List<LessonDraft> {
    return mapNotNull { it.toLessonDraft() }
}

fun LessonDetails.toLessonDraft(): LessonDraft? {
    return when (this) {
        is LessonDetails.Default -> when (this.type) {
            DefaultLessonType.Default -> LessonDraft.Default(
                id = id,
                name = name,
                difficulty = difficulty.toLessonDifficultyDomainModel()
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
            difficulty = difficulty.toLessonDifficultyDomainModel()
        )
    }
}
