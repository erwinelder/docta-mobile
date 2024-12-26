package cz.cvut.docta.lesson.data.model

sealed class LessonDetails(
    open val sectionId: Long,
    open val id: Long,
    open val orderNum: Int,
    open val name: String,
    open val difficulty: String
) {

    data class Default(
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val difficulty: String,
        val type: String,
        val matchAllTags: Boolean
    ) : LessonDetails(sectionId, id, orderNum, name, difficulty)

    data class StepByStep(
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val difficulty: String,
        val description: String
    ) : LessonDetails(sectionId, id, orderNum, name, difficulty)

}