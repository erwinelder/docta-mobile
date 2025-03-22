package cz.cvut.docta.course.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseWithProgressDto(
    val code: String,
    val locale: CourseLocaleDto,
    val name: String,
    val sectionCount: Int,
    val completedCount: Int
)