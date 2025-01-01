package cz.cvut.docta.question.domain.model

import cz.cvut.docta.answer.domain.model.AnswerText

sealed class Question(
    open val id: Long
) {

    data class OpenAnswer(
        override val id: Long,
        val difficulty: QuestionDifficulty,
        val text: String,
    ) : Question(id)

    data class FillInBlanks(
        override val id: Long,
        val difficulty: QuestionDifficulty,
        val units: List<FillInBlankQuestionUnit>
    ) : Question(id) {

        fun getBlankNumbers(): List<Int> {
            return units.mapNotNull {
                (it as? FillInBlankQuestionUnit.BlankNumber)?.number
            }
        }

        companion object {

            fun fromText(
                id: Long,
                difficulty: QuestionDifficulty,
                text: String
            ): FillInBlanks {
                var blankCount = 0
                val units = text
                    .split(" ")
                    .map { string ->
                        if (string == "___") {
                            FillInBlankQuestionUnit.BlankNumber(number = blankCount++)
                        } else {
                            FillInBlankQuestionUnit.Word(word = string)
                        }
                    }

                return FillInBlanks(id = id, difficulty = difficulty, units = units)
            }
        }

    }

    data class AnswerOptions(
        override val id: Long,
        val difficulty: QuestionDifficulty,
        val text: String,
        val options: List<AnswerText>
    ) : Question(id)

    data class QuestionAnswerPairs(
        override val id: Long,
        val difficulty: QuestionDifficulty,
        val questionPairs: List<AnswerText>,
        val answerPairs: List<AnswerText>
    ) : Question(id)

    data class StepByStep(
        override val id: Long,
        val text: String
    ) : Question(id)

}