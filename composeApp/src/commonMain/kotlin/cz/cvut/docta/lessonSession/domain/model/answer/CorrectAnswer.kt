package cz.cvut.docta.lessonSession.domain.model.answer

sealed class CorrectAnswer(
    open val questionId: Long
) {

    data class OpenAnswers(
        override val questionId: Long,
        val answers: List<String>
    ) : CorrectAnswer(questionId) {

        fun checkAnswer(answer: String): Boolean {
            return answers.contains(answer.trim())
        }

    }

    data class Blanks(
        override val questionId: Long,
        val blanksAnswers: Map<Int, List<String>>
    ) : CorrectAnswer(questionId) {

        fun getBlankNumbers(): List<Int> {
            return blanksAnswers.keys.toList()
        }

        fun getWrongBlanksWithCorrectAnswer(answers: Map<Int, String>): Map<Int, String> {
            return answers.mapNotNull { (blankNum, answer) ->
                val correctAnswers = blanksAnswers[blankNum] ?: return@mapNotNull null

                if (correctAnswers.contains(answer.trim())) return@mapNotNull null

                correctAnswers.firstOrNull()?.let { blankNum to it }
            }.toMap()
        }

    }

    data class Option(
        override val questionId: Long,
        val id: Long
    ) : CorrectAnswer(questionId) {

        fun checkOption(id: Long?): Boolean {
            return this.id == id
        }

    }

    data class StepAnswer(
        override val questionId: Long,
        val answer: String
    ) : CorrectAnswer(questionId) {

        fun checkAnswer(answer: String): Boolean {
            return this.answer == answer
        }

    }

}