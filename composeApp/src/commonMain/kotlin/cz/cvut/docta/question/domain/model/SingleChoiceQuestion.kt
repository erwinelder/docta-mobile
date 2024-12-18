package cz.cvut.docta.question.domain.model

data class SingleChoiceQuestion(
    val questionId: Long,
    val correctAnswerId: Long
)
