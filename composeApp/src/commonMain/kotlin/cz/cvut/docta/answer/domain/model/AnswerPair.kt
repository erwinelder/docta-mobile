package cz.cvut.docta.answer.domain.model

data class AnswerPair(
    val id: Long,
    val firstAnswerId: Long,
    val secondAnswerId: Long
)
