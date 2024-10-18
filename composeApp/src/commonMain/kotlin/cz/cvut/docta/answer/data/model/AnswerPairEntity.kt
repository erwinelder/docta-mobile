package cz.cvut.docta.answer.data.model

data class AnswerPairEntity(
    val id: Long,
    val firstAnswerId: Long,
    val secondAnswerId: Long
)
