package cz.cvut.docta.lessonSession.data.model.answer

import kotlinx.serialization.Serializable

@Serializable
data class AnswerTextDto(
    val id: Long,
    val text: String
)