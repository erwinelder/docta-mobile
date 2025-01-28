package cz.cvut.docta.question.domain.model

import cz.cvut.docta.question.presentation.model.QuestionAndAnswersWrapper

data class QuestionWithCheckResult(
    val question: QuestionAndAnswersWrapper,
    val result: QuestionCheckResult
)