package cz.cvut.docta.answer.presentation.model

import cz.cvut.docta.answer.domain.model.QuestionAnswerPairItemState

sealed class AnswerInput {


    data class Open(
        val answer: String
    ) : AnswerInput() {

        fun onInputChange(value: String): Open {
            return copy(answer = value)
        }

    }


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

        fun getAnswerText(blankNum: Int): String {
            return answers[blankNum] ?: ""
        }

        fun onBlankInputChange(blankNum: Int, value: String): Blanks {
            val newAnswers = answers.toMutableMap()
            newAnswers[blankNum] = value
            return copy(answers = newAnswers)
        }

    }


    data class Option(
        val id: Long?
    ) : AnswerInput() {

        fun onOptionChoose(id: Long): Option {
            return copy(id = id)
        }

    }


    data class QuestionAnswerPair(
        val questions: Map<Long, QuestionAnswerPairItemState>,
        val answers: Map<Long, QuestionAnswerPairItemState>
    ) : AnswerInput() {

        companion object {

            fun fromIds(
                questionIds: List<Long>,
                answerIds: List<Long>
            ): QuestionAnswerPair {
                return QuestionAnswerPair(
                    questions = questionIds.associateWith { QuestionAnswerPairItemState() },
                    answers = answerIds.associateWith { QuestionAnswerPairItemState() }
                )
            }

        }

        fun onQuestionSelect(id: Long): QuestionAnswerPair {
            answers.forEach { (answerId, answerState) ->
                if (answerState.isSelected) {
                    val isCorrect = id == answerId
                    return copy(
                        questions = questions
                            .markAsCorrectOrIncorrect(id = id, isCorrect = isCorrect),
                        answers = answers
                            .markAsCorrectOrIncorrect(id = answerId, isCorrect = isCorrect)
                    )
                }
            }

            return copy(questions = questions.markAsSelected(id))
        }

        fun onAnswerSelect(id: Long): QuestionAnswerPair {
            questions.forEach { (questionId, questionState) ->
                if (questionState.isSelected) {
                    val isCorrect = id == questionId
                    return copy(
                        questions = questions
                            .markAsCorrectOrIncorrect(id = questionId, isCorrect = isCorrect),
                        answers = answers
                            .markAsCorrectOrIncorrect(id = id, isCorrect = isCorrect)
                    )
                }
            }

            return copy(answers = answers.markAsSelected(id))
        }

        fun stageChanges(): QuestionAnswerPair? {

            val newQuestions = questions
                .firstNotNullOfOrNull { item -> item.takeIf { it.value.isCorrect != null } }
                ?.let { (questionId, questionState) ->
                    if (questionState.isCorrect == true) {
                        questions.markAsDisabled(questionId)
                    } else {
                        questions.reset(questionId)
                    }
                }
                ?: return null

            val newAnswers = answers
                .firstNotNullOfOrNull { item -> item.takeIf { it.value.isCorrect != null } }
                ?.let { (answerId, answerState) ->
                    if (answerState.isCorrect == true) {
                        answers.markAsDisabled(answerId)
                    } else {
                        answers.reset(answerId)
                    }
                }
                ?: return null

            return copy(questions = newQuestions, answers = newAnswers)
        }

        fun allPairsAreMatched(): Boolean {
            return questions.all { (_, questionState) -> questionState.isDisabled }
        }

        private fun Map<Long, QuestionAnswerPairItemState>.markAsSelected(
            id: Long
        ): Map<Long, QuestionAnswerPairItemState> {
            return toMutableMap().apply {
                this[id]?.copy(isSelected = true)?.let {
                    this[id] = it
                }
            }
        }

        private fun Map<Long, QuestionAnswerPairItemState>.markAsCorrectOrIncorrect(
            id: Long,
            isCorrect: Boolean
        ): Map<Long, QuestionAnswerPairItemState> {
            return toMutableMap().apply {
                this[id]?.copy(isCorrect = isCorrect, isSelected = false)?.let {
                    this[id] = it
                }
            }
        }

        private fun Map<Long, QuestionAnswerPairItemState>.markAsDisabled(
            id: Long
        ): Map<Long, QuestionAnswerPairItemState> {
            return toMutableMap().apply {
                this[id]?.copy(isCorrect = null, isSelected = false, isDisabled = true)?.let {
                    this[id] = it
                }
            }
        }

        private fun Map<Long, QuestionAnswerPairItemState>.reset(
            id: Long
        ): Map<Long, QuestionAnswerPairItemState> {
            return toMutableMap().apply {
                this[id]?.copy(isCorrect = null, isSelected = false, isDisabled = true)?.let {
                    this[id] = it
                }
            }
        }

    }


    data class Step(
        val answer: String
    ) : AnswerInput() {

        fun onInputChange(value: String): Step {
            return copy(answer = value)
        }

    }


}