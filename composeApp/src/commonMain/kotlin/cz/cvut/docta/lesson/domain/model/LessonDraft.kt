package cz.cvut.docta.lesson.domain.model

sealed class LessonDraft(
    open val id: Long,
    open val name: String
) {

    data class Default(
        override val id: Long,
        override val name: String,
        val difficulty: LessonDifficulty
    ) : LessonDraft(id, name)

    data class StepByStep(
        override val id: Long,
        override val name: String,
        val description: String,
        val difficulty: LessonDifficulty
    ) : LessonDraft(id, name)

    data class Test(
        override val id: Long,
        override val name: String
    ) : LessonDraft(id, name)

}