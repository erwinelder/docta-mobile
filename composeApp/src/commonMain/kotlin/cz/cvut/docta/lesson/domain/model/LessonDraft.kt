package cz.cvut.docta.lesson.domain.model

sealed class LessonDraft(
    open val id: Int,
    open val name: String,
    open val description: String
) {

    data class Default(
        override val id: Int,
        override val name: String,
        override val description: String
    ) : LessonDraft(id, name, description)

    data class StepByStep(
        override val id: Int,
        override val name: String,
        override val description: String
    ) : LessonDraft(id, name, description)

    data class Test(
        override val id: Int,
        override val name: String,
        override val description: String
    ) : LessonDraft(id, name, description)

}