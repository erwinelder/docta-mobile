package cz.cvut.docta.lesson.mapper

import cz.cvut.docta.lesson.data.model.DefaultLessonTypeDto
import cz.cvut.docta.lesson.data.model.LessonDifficultyDto
import cz.cvut.docta.lesson.data.model.LessonDto
import cz.cvut.docta.lesson.data.model.LessonWithProgressDto
import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lesson.domain.model.LessonDraft


fun LessonDifficultyDto.toLessonDifficultyDomainModel(): cz.cvut.docta.lesson.domain.model.LessonDifficulty {
    return when(this) {
        LessonDifficultyDto.Easy -> cz.cvut.docta.lesson.domain.model.LessonDifficulty.Easy
        LessonDifficultyDto.Medium -> cz.cvut.docta.lesson.domain.model.LessonDifficulty.Medium
        LessonDifficultyDto.Hard -> cz.cvut.docta.lesson.domain.model.LessonDifficulty.Hard
    }
}


fun List<LessonWithProgressDto>.toDomainLessons(): List<LessonWithProgress> {
    return map { it.toDomainLesson() }
}

fun LessonWithProgressDto.toDomainLesson(): LessonWithProgress {
    return when (this) {
        is LessonWithProgressDto.DefaultLesson -> when (this.type) {
            DefaultLessonTypeDto.Default -> LessonWithProgress.Default(
                id = id,
                name = name,
                completed = completed,
                difficulty = difficulty.toLessonDifficultyDomainModel(),
                matchAllTags = matchAllTags
            )
            DefaultLessonTypeDto.Test -> LessonWithProgress.Test(
                id = id,
                name = name,
                completed = completed,
                matchAllTags = matchAllTags
            )
        }
        is LessonWithProgressDto.StepByStepLesson -> LessonWithProgress.StepByStep(
            id = id,
            name = name,
            completed = completed,
            difficulty = difficulty.toLessonDifficultyDomainModel(),
            description = description
        )
    }
}


fun List<LessonDto>.toLessonDraftList(): List<LessonDraft> {
    return mapNotNull { it.toLessonDraft() }
}

fun LessonDto.toLessonDraft(): LessonDraft? {
    return when (this) {
        is LessonDto.Default -> when (this.type) {
            DefaultLessonTypeDto.Default -> LessonDraft.Default(
                id = id,
                name = name,
                difficulty = difficulty.toLessonDifficultyDomainModel()
            )
            DefaultLessonTypeDto.Test -> LessonDraft.Test(
                id = id,
                name = name
            )
        }
        is LessonDto.StepByStep -> LessonDraft.StepByStep(
            id = id,
            name = name,
            description = description,
            difficulty = difficulty.toLessonDifficultyDomainModel()
        )
    }
}
