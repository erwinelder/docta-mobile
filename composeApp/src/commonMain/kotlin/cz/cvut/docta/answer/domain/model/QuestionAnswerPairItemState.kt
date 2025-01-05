package cz.cvut.docta.answer.domain.model

data class QuestionAnswerPairItemState(
    val isCorrect: Boolean? = null,
    val isDisabled: Boolean = false,
    val isSelected: Boolean = false
)
