package cz.cvut.docta.question.domain.model

import cz.cvut.docta.answer.domain.model.Answer
import cz.cvut.docta.question.data.model.QuestionType

data class Question(
    val id: Long,
    val type: QuestionType,
    val text: String,
    val allAnswers: List<Answer>
)
