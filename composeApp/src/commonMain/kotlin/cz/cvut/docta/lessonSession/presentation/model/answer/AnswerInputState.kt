package cz.cvut.docta.lessonSession.presentation.model.answer

import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText

sealed class AnswerInputState {

    data class Open(
        val answer: String
    ) : AnswerInputState()

    data class Blanks(
        val answers: Map<Int, String>
    ) : AnswerInputState() {

        companion object {

            fun fromText(text: String): Blanks {
                val blankCount = text.split("___").size - 1
                if (blankCount <= 0) {
                    return Blanks(answers = emptyMap())
                }

                val blanks = (1..blankCount).toList().associateWith { "" }

                return Blanks(answers = blanks)
            }

        }

    }

    data class SingleOption(
        val id: Long?
    ) : AnswerInputState()

    data class CategorizedOptions(
        val optionToCategoryMap: Map<Long, Long?>
    ) : AnswerInputState() {

        companion object{

            fun fromOptions(options: List<AnswerText>): CategorizedOptions {
                return CategorizedOptions(
                    optionToCategoryMap = options.map { it.id }.associateWith { null }
                )
            }

        }

    }

    data class OrderedOptions(
        val optionToOrderNumMap: Map<Long, Int>
    ) : AnswerInputState() {

        companion object{

            fun fromOptions(options: List<AnswerText>): OrderedOptions {
                return OrderedOptions(
                    optionToOrderNumMap = options
                        .mapIndexed { index, answerText ->
                            answerText.id to index
                        }
                        .toMap()
                )
            }

        }

    }

    data class QuestionAnswerPair(
        val questions: List<QuestionAnswerPairItemUiState>,
        val answers: List<QuestionAnswerPairItemUiState>
    ) : AnswerInputState() {

        companion object {

            fun fromAnswerTextList(
                questions: List<AnswerText>,
                answers: List<AnswerText>
            ): QuestionAnswerPair {
                return QuestionAnswerPair(
                    questions = questions.map {
                        QuestionAnswerPairItemUiState(id = it.id, text = it.text)
                    },
                    answers = answers.map {
                        QuestionAnswerPairItemUiState(id = it.id, text = it.text)
                    }
                )
            }

        }

    }

}