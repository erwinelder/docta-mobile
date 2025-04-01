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

            fun fromBlankNumbers(blanksNumbers: List<Int>): Blanks {
                return Blanks(
                    answers = blanksNumbers.associateWith { "" }
                )
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