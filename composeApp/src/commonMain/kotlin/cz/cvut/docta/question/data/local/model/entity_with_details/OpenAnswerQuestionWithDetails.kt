package cz.cvut.docta.question.data.local.model.entity_with_details

data class OpenAnswerQuestionWithDetails(
    val id: Long,
    val difficulty: String,
    val questionId: Long,
    val text: String
)
