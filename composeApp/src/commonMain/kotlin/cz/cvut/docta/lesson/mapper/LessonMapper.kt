package cz.cvut.docta.lesson.mapper

import cz.cvut.docta.lesson.data.model.DefaultLessonTypeDto
import cz.cvut.docta.lesson.data.model.LessonDto
import cz.cvut.docta.lesson.data.model.LessonWithProgressDto
import cz.cvut.docta.lesson.domain.model.LessonDraft
import cz.cvut.docta.lesson.domain.model.LessonWithProgress


fun List<LessonWithProgressDto>.toDomainLessons(): List<LessonWithProgress> {
    return map { it.toDomainLesson() }
}

fun LessonWithProgressDto.toDomainLesson(): LessonWithProgress {
    return when (this) {
        is LessonWithProgressDto.DefaultLesson -> when (this.type) {
            DefaultLessonTypeDto.Default -> LessonWithProgress.Default(
                id = id,
                name = name,
                description = description,
                completed = completed
            )
            DefaultLessonTypeDto.Test -> LessonWithProgress.Test(
                id = id,
                name = name,
                description = description,
                completed = completed
            )
        }
        is LessonWithProgressDto.StepByStepLesson -> LessonWithProgress.StepByStep(
            id = id,
            name = name,
            description = description,
            completed = completed
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
                description = description
            )
            DefaultLessonTypeDto.Test -> LessonDraft.Test(
                id = id,
                name = name,
                description = description
            )
        }
        is LessonDto.StepByStep -> LessonDraft.StepByStep(
            id = id,
            name = name,
            description = description
        )
    }
}
