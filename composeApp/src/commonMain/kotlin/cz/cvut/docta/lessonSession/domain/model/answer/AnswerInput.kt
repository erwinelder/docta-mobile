package cz.cvut.docta.lessonSession.domain.model.answer

sealed class AnswerInput(
    open val questionId: Long
) {

    data class Open(
        override val questionId: Long,
        val answer: String
    ) : AnswerInput(questionId)

    data class Blanks(
        override val questionId: Long,
        val answers: Map<Int, String>
    ) : AnswerInput(questionId)

    data class SingleOption(
        override val questionId: Long,
        val id: Long
    ) : AnswerInput(questionId)

    data class CategorizedOptions(
        override val questionId: Long,
        val optionToCategoryMap: Map<Long, Long>
    ) : AnswerInput(questionId)

    data class QuestionAnswerPairs(
        override val questionId: Long,
        val hadErrors: Boolean
    ) : AnswerInput(questionId)

}