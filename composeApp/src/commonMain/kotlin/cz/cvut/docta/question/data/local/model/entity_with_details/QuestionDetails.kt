package cz.cvut.docta.question.data.local.model.entity_with_details

sealed class QuestionDetails(
    open val id: Long,
    open val difficulty: String
) {

    data class OpenAnswer(
        override val id: Long,
        override val difficulty: String,
        val text: String
    ) : QuestionDetails(id, difficulty)

    data class FillInBlanks(
        override val id: Long,
        override val difficulty: String,
        val text: String
    ) : QuestionDetails(id, difficulty)

    data class AnswerOptions(
        override val id: Long,
        override val difficulty: String,
        val text: String,
        val correctOptionId: Long
    ) : QuestionDetails(id, difficulty)

    data class QuestionAnswerPairs(
        override val id: Long,
        override val difficulty: String
    ) : QuestionDetails(id, difficulty)

}