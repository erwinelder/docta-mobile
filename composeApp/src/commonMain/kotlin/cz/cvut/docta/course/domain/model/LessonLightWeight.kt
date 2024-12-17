package cz.cvut.docta.course.domain.model

data class LessonLightWeight(
    val id: Long,
    val orderNum: Int,
    val name: String,
    val isTest: Boolean
)
