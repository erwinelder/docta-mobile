package cz.cvut.docta.lessonSession.domain.model

import cz.cvut.docta.lessonSession.presentation.model.QuestionWrapper

data class QuestionWithCheckResult(
    val question: QuestionWrapper,
    val isCorrect: Boolean
)