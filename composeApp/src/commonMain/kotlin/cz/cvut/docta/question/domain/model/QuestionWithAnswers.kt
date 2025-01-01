package cz.cvut.docta.question.domain.model

import cz.cvut.docta.answer.domain.model.CorrectAnswer

sealed class QuestionWithAnswers {

    data class OpenAnswer(
        val question: Question.OpenAnswer,
        val answers: CorrectAnswer.OpenAnswers
    ) : QuestionWithAnswers()

    data class FillInBlanks(
        val question: Question.FillInBlanks,
        val blanksAnswers: CorrectAnswer.Blanks
    ) : QuestionWithAnswers()

    data class AnswerOptions(
        val question: Question.AnswerOptions,
        val answer: CorrectAnswer.Option
    ) : QuestionWithAnswers()

    data class QuestionAnswerPairs(
        val question: Question.QuestionAnswerPairs
    ) : QuestionWithAnswers()

    data class StepByStep(
        val question: Question.StepByStep,
        val answer: CorrectAnswer.StepAnswer
    ) : QuestionWithAnswers()


    fun getQuestion(): Question {
        return when (this) {
            is OpenAnswer -> question
            is FillInBlanks -> question
            is AnswerOptions -> question
            is QuestionAnswerPairs -> question
            is StepByStep -> question
        }
    }

}