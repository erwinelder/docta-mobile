package cz.cvut.docta.lesson.data.model

data class DefaultLessonWithDetails(
    val sectionId: Long,
    val id: Long,
    val orderNum: Int,
    val name: String,
    val difficulty: String,
    val type: String,
    val matchAllTags: Boolean
)
