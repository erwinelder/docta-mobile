package cz.cvut.docta.lesson.data.remote.model.entity_with_details

data class StepByStepLessonRemoteWithDetails(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    val sectionId: Long,
    val id: Long,
    val type: String,
    val orderNum: Int,
    val name: String,
    val difficulty: String,
    val lessonId: Long,
    val description: String
)
