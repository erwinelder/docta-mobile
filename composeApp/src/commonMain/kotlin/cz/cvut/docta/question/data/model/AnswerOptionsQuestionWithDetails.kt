package cz.cvut.docta.question.data.model

data class AnswerOptionsQuestionWithDetails(
    val id: Long,
    val difficulty: String,
    val questionId: Long,
    val text: String,
    val correctOptionId: Long
)
