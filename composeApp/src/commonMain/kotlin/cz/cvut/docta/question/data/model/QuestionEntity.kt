package cz.cvut.docta.question.data.model

sealed class QuestionEntity(
    open val id: Long,
    open val type: QuestionType,
    open val text: String
)
