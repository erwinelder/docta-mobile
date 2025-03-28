package cz.cvut.docta.section.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SectionWithProgressDto(
    val id: Int,
    val courseCode: String,
    val orderNum: Int,
    val name: String,
    val lessonCount: Int,
    val completedCount: Int
)
