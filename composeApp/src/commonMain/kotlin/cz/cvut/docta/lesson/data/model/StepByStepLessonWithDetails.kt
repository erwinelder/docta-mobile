package cz.cvut.docta.lesson.data.model

data class StepByStepLessonWithDetails(
    val sectionId: Long,
    val id: Long,
    val type: String,
    val orderNum: Int,
    val name: String,
    val difficulty: String,
    val lessonId: Long,
    val description: String
)
