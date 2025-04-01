package cz.cvut.docta.lessonSession.presentation.model

data class QuestionWithCheckResult(
    val question: QuestionWrapper,
    val isCorrect: Boolean
)