package cz.cvut.docta.lessonSession.presentation.model.answer

import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText

sealed class AnswerInput {

    data class Open(
        val answer: String
    ) : AnswerInput()

    data class Blanks(
        val answers: Map<Int, String>
    ) : AnswerInput() {

        companion object {

            fun fromBlankNumbers(blanksNumbers: List<Int>): Blanks {
                return Blanks(
                    answers = blanksNumbers.associateWith { "" }
                )
            }

        }

    }

    data class Option(
        val id: Long?
    ) : AnswerInput()

    data class QuestionAnswerPair(
        val questions: List<QuestionAnswerPairItemUiState>,
        val answers: List<QuestionAnswerPairItemUiState>
    ) : AnswerInput() {

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

    data class CategorizationQuestion(
        val optionsCategory: Map<Long, Long?>
    ) : AnswerInput() {
        companion object{
            fun fromOptions(options: List<AnswerText>): CategorizationQuestion {
                return CategorizationQuestion(
                    optionsCategory = options.map{ it.id
                    }.associateWith { null }
                )
            }
        }
    }

    data class Step(
        val answer: String
    ) : AnswerInput()

}