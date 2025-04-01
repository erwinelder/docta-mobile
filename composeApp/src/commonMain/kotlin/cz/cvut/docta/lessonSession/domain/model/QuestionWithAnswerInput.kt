package cz.cvut.docta.lessonSession.domain.model

import cz.cvut.docta.lessonSession.domain.model.question.Question
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInputState

sealed class QuestionWithAnswerInput(
    open val question: Question,
    open val answerInput: AnswerInputState
) {

    data class OpenAnswer(
        override val question: Question.OpenAnswer,
        override val answerInput: AnswerInputState.Open
    ) : QuestionWithAnswerInput(question, answerInput)

    data class FillInBlanks(
        override val question: Question.FillInBlanks,
        override val answerInput: AnswerInputState.Blanks
    ) : QuestionWithAnswerInput(question, answerInput)

    data class AnswerOptions(
        override val question: Question.SingleOption,
        override val answerInput: AnswerInputState.SingleOption
    ) : QuestionWithAnswerInput(question, answerInput)

    data class Categorization(
        override val question: Question.Categorization,
        override val answerInput: AnswerInputState.CategorizedOptions
    ) : QuestionWithAnswerInput(question, answerInput)

    data class QuestionAnswerPairs(
        override val question: Question.QuestionAnswerPairs,
        override val answerInput: AnswerInputState.QuestionAnswerPair
    ) : QuestionWithAnswerInput(question, answerInput)

}