package cz.cvut.docta.lessonSession.presentation.model

import cz.cvut.docta.lessonSession.domain.model.answer.CorrectAnswer
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInput
import cz.cvut.docta.lessonSession.domain.model.question.Question
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCorrectAnswers
import cz.cvut.docta.lessonSession.presentation.model.question.Materials
import kotlin.collections.List

sealed class QuestionAndAnswersWrapper {

    data class OpenAnswer(
        val question: Question.OpenAnswer,
        val materials: List<Materials>,
        val correctAnswer: CorrectAnswer.OpenAnswers,
        val answerInput: AnswerInput.Open
    ) : QuestionAndAnswersWrapper()

    data class FillInBlanks(
        val question: Question.FillInBlanks,
        val materials: List<Materials>,
        val correctAnswer: CorrectAnswer.Blanks,
        val answerInput: AnswerInput.Blanks
    ) : QuestionAndAnswersWrapper()

    data class AnswerOptions(
        val question: Question.AnswerOptions,
        val materials: List<Materials>,
        val correctAnswer: CorrectAnswer.Option,
        val answerInput: AnswerInput.Option
    ) : QuestionAndAnswersWrapper()

    data class Categorization(
        val question: Question.Categorization,
        val materials: List<Materials>,
        val correctAnswer: CorrectAnswer.CategorizedOptions,
        val answerInput: AnswerInput.CategorizedOptions
    ) : QuestionAndAnswersWrapper()

    data class QuestionAnswerPairs(
        val question: Question.QuestionAnswerPairs,
        val materials: List<Materials>,
        val answerInput: AnswerInput.QuestionAnswerPair
    ) : QuestionAndAnswersWrapper()


    companion object {

        fun fromQuestion(
            questionWithCorrectAnswers: QuestionWithCorrectAnswers
        ): QuestionAndAnswersWrapper {
            return when (questionWithCorrectAnswers) {
                is QuestionWithCorrectAnswers.OpenAnswer -> OpenAnswer(
                    question = questionWithCorrectAnswers.question,
                    materials = questionWithCorrectAnswers.materials.map {
                        Materials(id = it.id, text = it.text)
                    },
                    correctAnswer = questionWithCorrectAnswers.answers,
                    answerInput = AnswerInput.Open(answer = "")
                )
                is QuestionWithCorrectAnswers.FillInBlanks -> FillInBlanks(
                    question = questionWithCorrectAnswers.question,
                    materials = questionWithCorrectAnswers.materials.map {
                        Materials(id = it.id, text = it.text)
                    },
                    correctAnswer = questionWithCorrectAnswers.blanksAnswers,
                    answerInput = AnswerInput.Blanks.fromBlankNumbers(
                        blanksNumbers = questionWithCorrectAnswers.blanksAnswers.getBlankNumbers()
                    )
                )
                is QuestionWithCorrectAnswers.AnswerOptions -> AnswerOptions(
                    question = questionWithCorrectAnswers.question,
                    materials = questionWithCorrectAnswers.materials.map {
                        Materials(id = it.id, text = it.text)
                    },
                    correctAnswer = questionWithCorrectAnswers.answer,
                    answerInput = AnswerInput.Option(id = null)
                )
                is QuestionWithCorrectAnswers.Categorization -> Categorization(
                    question = questionWithCorrectAnswers.question,
                    materials = questionWithCorrectAnswers.materials.map {
                        Materials(id = it.id, text = it.text)
                    },
                    correctAnswer = questionWithCorrectAnswers.categoriesOptions,
                    answerInput = AnswerInput.CategorizedOptions.fromOptions(
                        options = questionWithCorrectAnswers.question.options
                    )
                )
                is QuestionWithCorrectAnswers.QuestionAnswerPairs -> QuestionAnswerPairs(
                    question = questionWithCorrectAnswers.question,
                    materials = questionWithCorrectAnswers.materials.map {
                        Materials(id = it.id, text = it.text)
                    },
                    answerInput = AnswerInput.QuestionAnswerPair.fromAnswerTextList(
                        questions = questionWithCorrectAnswers.question.questionPairs,
                        answers = questionWithCorrectAnswers.question.answerPairs
                    )
                )
            }
        }
    }
}