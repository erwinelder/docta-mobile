package cz.cvut.docta.lessonSession.domain.model

import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.presentation.model.QuestionAndAnswersWrapper

data class QuestionWithCheckResult(
    val question: QuestionAndAnswersWrapper,
    val result: QuestionCheckResult
)