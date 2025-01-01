package cz.cvut.docta.question.data.model

data class FillInBlanksQuestionWithDetails(
    val id: Long,
    val difficulty: String,
    val questionId: Long,
    val text: String
)
