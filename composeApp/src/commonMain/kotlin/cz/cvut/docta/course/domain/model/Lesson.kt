package cz.cvut.docta.course.domain.model

data class Lesson(
    val id: Long,
    val orderNum: Int,
    val name: String,
    val isTest: Boolean,
    val isCompleted: Boolean,
    val questions: List<Question>
)
