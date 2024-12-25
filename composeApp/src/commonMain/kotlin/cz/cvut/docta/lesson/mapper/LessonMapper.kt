package cz.cvut.docta.lesson.mapper

import cz.cvut.docta.lesson.data.model.LessonEntity
import cz.cvut.docta.lesson.domain.model.Lesson

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

fun Lesson.toEntity(): LessonEntity {
    return LessonEntity(
        id = this.id,
        name = this.name,
        isDone = this.isDone,
        difficulty = if (this is Lesson.OneStepQuestionsLesson) this.difficulty else if (this is Lesson.StepByStepLesson) this.difficulty else null,
        description = if (this is Lesson.StepByStepLesson) this.description else null
    )
}
