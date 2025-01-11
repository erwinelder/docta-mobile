package cz.cvut.docta.question.data.remote.model

sealed class QuestionRemoteDetails(
    open val updateTime: Long,
    open val deleted: Boolean,
    open val courseCode: String,
    open val id: Long,
    open val difficulty: String
) {

    data class OpenAnswer(
        override val updateTime: Long,
        override val deleted: Boolean,
        override val courseCode: String,
        override val id: Long,
        override val difficulty: String,
        val text: String
    ) : QuestionRemoteDetails(updateTime, deleted, courseCode, id, difficulty)

    data class FillInBlanks(
        override val updateTime: Long,
        override val deleted: Boolean,
        override val courseCode: String,
        override val id: Long,
        override val difficulty: String,
        val text: String
    ) : QuestionRemoteDetails(updateTime, deleted, courseCode, id, difficulty)

    data class AnswerOptions(
        override val updateTime: Long,
        override val deleted: Boolean,
        override val courseCode: String,
        override val id: Long,
        override val difficulty: String,
        val text: String,
        val correctOptionId: Long
    ) : QuestionRemoteDetails(updateTime, deleted, courseCode, id, difficulty)

    data class QuestionAnswerPairs(
        override val updateTime: Long,
        override val deleted: Boolean,
        override val courseCode: String,
        override val id: Long,
        override val difficulty: String
    ) : QuestionRemoteDetails(updateTime, deleted, courseCode, id, difficulty)

}