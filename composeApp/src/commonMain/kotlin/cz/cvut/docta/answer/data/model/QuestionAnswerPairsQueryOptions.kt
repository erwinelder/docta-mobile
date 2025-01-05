package cz.cvut.docta.answer.data.model

data class QuestionAnswerPairsQueryOptions(
    val courseCode: String,
    val difficulty: String,
    val questionId: Long
)
