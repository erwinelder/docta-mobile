package cz.cvut.docta.answer.data.local.model

data class QuestionAnswerPairsQueryOptions(
    val courseCode: String,
    val difficulty: String,
    val questionId: Long
)
