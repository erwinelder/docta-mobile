package cz.cvut.docta.section.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SectionDto(
    val courseCode: String,
    val id: Int,
    val orderNum: Int,
    val name: String,
    val lessonCount: Int
)