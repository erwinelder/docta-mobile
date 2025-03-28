package cz.cvut.docta.course.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    val code: String,
    val locale: CourseLocaleDto,
    val name: String,
    val sectionCount: Int
)