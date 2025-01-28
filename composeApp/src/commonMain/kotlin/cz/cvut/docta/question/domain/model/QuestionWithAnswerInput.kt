package cz.cvut.docta.question.domain.model

import cz.cvut.docta.answer.presentation.model.AnswerInput

sealed class QuestionWithAnswerInput(
    open val question: Question,
    open val answerInput: AnswerInput
) {

    data class OpenAnswer(
        override val question: Question.OpenAnswer,
        override val answerInput: AnswerInput.Open
    ) : QuestionWithAnswerInput(question, answerInput)

    data class FillInBlanks(
        override val question: Question.FillInBlanks,
        override val answerInput: AnswerInput.Blanks
    ) : QuestionWithAnswerInput(question, answerInput)

    data class AnswerOptions(
        override val question: Question.AnswerOptions,
        override val answerInput: AnswerInput.Option
    ) : QuestionWithAnswerInput(question, answerInput)

    data class QuestionAnswerPairs(
        override val question: Question.QuestionAnswerPairs,
        override val answerInput: AnswerInput.QuestionAnswerPair
    ) : QuestionWithAnswerInput(question, answerInput)

    data class Step(
        override val question: Question.StepByStep,
        override val answerInput: AnswerInput.Step
    ) : QuestionWithAnswerInput(question, answerInput)


    // TODO: Remove this
    /*companion object {

        fun fromQuestion(question: Question): QuestionWithAnswerInput {
            return when (question) {
                is Question.OpenAnswer -> OpenAnswer(
                    question = question,
                    answerInput = AnswerInput.Open(answer = ""),
                    checkIsAllowed = false
                )
                is Question.FillInBlanks -> FillInBlanks(
                    question = question,
                    answerInput = AnswerInput.Blanks.fromBlankNumbers(question.getBlankNumbers()),
                    checkIsAllowed = false
                )
                is Question.AnswerOptions -> AnswerOptions(
                    question = question,
                    answerInput = AnswerInput.Option(id = null),
                    checkIsAllowed = false
                )
                is Question.QuestionAnswerPairs -> QuestionAnswerPairs(
                    question = question,
                    answerInput = AnswerInput.QuestionAnswerPair.fromIds(
                        questionIds = question.questionPairs.map { it.id },
                        answerIds = question.answerPairs.map { it.id }
                    )
                )
                is Question.StepByStep -> Step(
                    question = question,
                    answerInput = AnswerInput.Step(answer = ""),
                    checkIsAllowed = false
                )
            }
        }

    }*/

}