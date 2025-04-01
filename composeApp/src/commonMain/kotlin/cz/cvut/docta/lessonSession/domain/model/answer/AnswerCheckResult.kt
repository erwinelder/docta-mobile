package cz.cvut.docta.lessonSession.domain.model.answer

sealed class AnswerCheckResult(
    open val isCorrect: Boolean
) {

    data class Open(
        override val isCorrect: Boolean,
        val answer: String
    ) : AnswerCheckResult(isCorrect) {

        fun takeIfIncorrect(): Open? {
            return if (!isCorrect) this else null
        }

    }

    data class Blanks(
        override val isCorrect: Boolean,
        val answers: Map<Int, String>
    ) : AnswerCheckResult(isCorrect)

    data class SingleOption(
        override val isCorrect: Boolean,
        val id: Long
    ) : AnswerCheckResult(isCorrect)

    data class CategorizedOptions(
        override val isCorrect: Boolean,
        val optionToCategory: Map<Long, Long>
    ) : AnswerCheckResult(isCorrect)

    data class QuestionAnswerPairs(
        override val isCorrect: Boolean
    ) : AnswerCheckResult(isCorrect)

}