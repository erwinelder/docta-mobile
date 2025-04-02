package cz.cvut.docta.lessonSession.presentation.model

data class QuestionCheckResult(
    val question: QuestionWrapper,
    val isCorrect: Boolean
)