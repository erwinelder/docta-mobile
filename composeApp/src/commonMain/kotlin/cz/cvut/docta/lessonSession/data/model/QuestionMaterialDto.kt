package cz.cvut.docta.lessonSession.data.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionMaterialDto (
    val id: Int,
    val text: String,
    val questionIds: List<Long>
)