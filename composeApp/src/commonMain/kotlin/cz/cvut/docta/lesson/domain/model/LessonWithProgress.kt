package cz.cvut.docta.lesson.domain.model

sealed class LessonWithProgress(
    open val id: Int,
    open val name: String,
    open val completed: Boolean
) {

    data class Default(
        override val id: Int,
        override val name: String,
        override val completed: Boolean,
        val difficulty: LessonDifficulty,
        val matchAllTags: Boolean
    ) : LessonWithProgress(id, name, completed)

    data class StepByStep(
        override val id: Int,
        override val name: String,
        override val completed: Boolean,
        val description: String,
        val difficulty: LessonDifficulty
    ) : LessonWithProgress(id, name, completed)

    data class Test(
        override val id: Int,
        override val name: String,
        override val completed: Boolean,
        val matchAllTags: Boolean
    ) : LessonWithProgress(id, name, completed)

}