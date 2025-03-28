package cz.cvut.docta.lesson.domain.model

sealed class LessonWithProgress(
    open val id: Int,
    open val name: String,
    open val description: String,
    open val completed: Boolean
) {

    data class Default(
        override val id: Int,
        override val name: String,
        override val description: String,
        override val completed: Boolean
    ) : LessonWithProgress(id, name, description, completed)

    data class StepByStep(
        override val id: Int,
        override val name: String,
        override val description: String,
        override val completed: Boolean
    ) : LessonWithProgress(id, name, description, completed)

    data class Test(
        override val id: Int,
        override val name: String,
        override val description: String,
        override val completed: Boolean
    ) : LessonWithProgress(id, name, description, completed)

}