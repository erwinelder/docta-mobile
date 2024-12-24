package cz.cvut.docta.lesson.domain.model

sealed class Lesson(
    open val id: Long,
    open val name: String,
    open val isDone: Boolean
) {

    data class OneStepQuestionsLesson(
        override val id: Long,
        override val name: String,
        override val isDone: Boolean,
        val difficulty: LessonDifficulty,
    ) : Lesson(id, name, isDone)

    data class StepByStepLesson(
        override val id: Long,
        override val name: String,
        override val isDone: Boolean,
        val description: String,
        val difficulty: LessonDifficulty,
    ) : Lesson(id, name, isDone)

    data class TestLesson(
        override val id: Long,
        override val name: String,
        override val isDone: Boolean,
    ) : Lesson(id, name, isDone)

}
