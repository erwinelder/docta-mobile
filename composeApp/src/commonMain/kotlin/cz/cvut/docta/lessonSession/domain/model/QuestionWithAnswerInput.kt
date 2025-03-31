package cz.cvut.docta.lessonSession.domain.model

import cz.cvut.docta.lessonSession.domain.model.question.Question
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInput

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

    data class Categorization(
        override val question: Question.Categorization,
        override val answerInput: AnswerInput.CategorizedOptions
    ) : QuestionWithAnswerInput(question, answerInput)

    data class QuestionAnswerPairs(
        override val question: Question.QuestionAnswerPairs,
        override val answerInput: AnswerInput.QuestionAnswerPair
    ) : QuestionWithAnswerInput(question, answerInput)

}