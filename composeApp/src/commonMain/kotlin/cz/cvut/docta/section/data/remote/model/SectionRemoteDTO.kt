package cz.cvut.docta.section.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SectionRemoteDTO(
    val courseCode: String,
    val id: Long,
    val orderNum: Int,
    val name: String
)
