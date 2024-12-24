package cz.cvut.docta.lesson.mapper

import cz.cvut.docta.lesson.data.model.LessonEntity
import cz.cvut.docta.lesson.domain.model.Lesson

// TODO-LESSON

fun List<LessonEntity>.toDomainModels(): List<Lesson> {
    return map { it.toDomainModel() }
}

fun LessonEntity.toDomainModel(): Lesson {
    return when {
        difficulty != null && description != null -> Lesson.StepByStepLesson(
            id = this.id,
            name = this.name,
            isDone = this.isDone,
            description = this.description,
            difficulty = this.difficulty
        )
        difficulty != null -> Lesson.OneStepQuestionsLesson(
            id = this.id,
            name = this.name,
            isDone = this.isDone,
            difficulty = this.difficulty
        )
        else -> Lesson.TestLesson(
            id = this.id,
            name = this.name,
            isDone = this.isDone
        )
    }
}
