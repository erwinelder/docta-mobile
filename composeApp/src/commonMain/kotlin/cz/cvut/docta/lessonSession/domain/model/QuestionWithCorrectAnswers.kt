package cz.cvut.docta.lessonSession.domain.model

import cz.cvut.docta.lessonSession.domain.model.answer.CorrectAnswer
import cz.cvut.docta.lessonSession.domain.model.question.Question

sealed class QuestionWithCorrectAnswers {

    data class OpenAnswer(
        val question: Question.OpenAnswer,
        val materials: List<QuestionMaterials>,
        val answers: CorrectAnswer.OpenAnswers
    ) : QuestionWithCorrectAnswers()

    data class FillInBlanks(
        val question: Question.FillInBlanks,
        val materials: List<QuestionMaterials>,
        val blanksAnswers: CorrectAnswer.Blanks
    ) : QuestionWithCorrectAnswers()

    data class AnswerOptions(
        val question: Question.AnswerOptions,
        val materials: List<QuestionMaterials>,
        val answer: CorrectAnswer.Option
    ) : QuestionWithCorrectAnswers()

    data class Categorization(
        val question: Question.Categorization,
        val materials: List<QuestionMaterials>,
        val categoriesOptions: CorrectAnswer.CategorizedOptions
    ) : QuestionWithCorrectAnswers()

    data class QuestionAnswerPairs(
        val question: Question.QuestionAnswerPairs,
        val materials: List<QuestionMaterials>,
    ) : QuestionWithCorrectAnswers()


    fun getQuestion(): Question {
        return when (this) {
            is OpenAnswer -> question
            is FillInBlanks -> question
            is AnswerOptions -> question
            is Categorization -> question
            is QuestionAnswerPairs -> question
        }
    }

}