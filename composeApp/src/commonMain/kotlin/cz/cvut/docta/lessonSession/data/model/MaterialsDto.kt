package cz.cvut.docta.lessonSession.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MaterialsDto (
    val id: Long,
    val text: String
)