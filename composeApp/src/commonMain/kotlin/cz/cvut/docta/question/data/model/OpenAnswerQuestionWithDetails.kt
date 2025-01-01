package cz.cvut.docta.question.data.model

data class OpenAnswerQuestionWithDetails(
    val id: Long,
    val difficulty: String,
    val questionId: Long,
    val text: String
)
