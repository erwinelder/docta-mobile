package cz.cvut.docta.course.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseRemoteDTO(
    val code: String,
    val locale: String,
    val name: String
)
