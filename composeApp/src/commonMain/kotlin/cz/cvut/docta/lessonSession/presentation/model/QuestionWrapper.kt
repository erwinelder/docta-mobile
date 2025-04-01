package cz.cvut.docta.lessonSession.presentation.model

import cz.cvut.docta.lessonSession.domain.model.answer.CorrectAnswer
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInputState
import cz.cvut.docta.lessonSession.domain.model.question.Question
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCorrectAnswers
import cz.cvut.docta.lessonSession.domain.model.Materials
import kotlin.collections.List

sealed class QuestionWrapper {

    data class OpenAnswer(
        val question: Question.OpenAnswer,
        val materials: List<Materials>,
        val correctAnswer: CorrectAnswer.OpenAnswers,
        val answerInput: AnswerInputState.Open
    ) : QuestionWrapper()

    data class FillInBlanks(
        val question: Question.FillInBlanks,
        val materials: List<Materials>,
        val correctAnswer: CorrectAnswer.Blanks,
        val answerInput: AnswerInputState.Blanks
    ) : QuestionWrapper()

    data class AnswerOptions(
        val question: Question.AnswerOptions,
        val materials: List<Materials>,
        val correctAnswer: CorrectAnswer.Option,
        val answerInput: AnswerInputState.SingleOption
    ) : QuestionWrapper()

    data class Categorization(
        val question: Question.Categorization,
        val materials: List<Materials>,
        val correctAnswer: CorrectAnswer.CategorizedOptions,
        val answerInput: AnswerInputState.CategorizedOptions
    ) : QuestionWrapper()

    data class QuestionAnswerPairs(
        val question: Question.QuestionAnswerPairs,
        val materials: List<Materials>,
        val answerInput: AnswerInputState.QuestionAnswerPair
    ) : QuestionWrapper()


    companion object {

        fun fromQuestion(
            questionWithCorrectAnswers: QuestionWithCorrectAnswers
        ): QuestionWrapper {
            return when (questionWithCorrectAnswers) {
                is QuestionWithCorrectAnswers.OpenAnswer -> OpenAnswer(
                    question = questionWithCorrectAnswers.question,
                    materials = questionWithCorrectAnswers.materials.map {
                        Materials(id = it.id, text = it.text)
                    },
                    correctAnswer = questionWithCorrectAnswers.answers,
                    answerInput = AnswerInputState.Open(answer = "")
                )
                is QuestionWithCorrectAnswers.FillInBlanks -> FillInBlanks(
                    question = questionWithCorrectAnswers.question,
                    materials = questionWithCorrectAnswers.materials.map {
                        Materials(id = it.id, text = it.text)
                    },
                    correctAnswer = questionWithCorrectAnswers.blanksAnswers,
                    answerInput = AnswerInputState.Blanks.fromBlankNumbers(
                        blanksNumbers = questionWithCorrectAnswers.blanksAnswers.getBlankNumbers()
                    )
                )
                is QuestionWithCorrectAnswers.AnswerOptions -> AnswerOptions(
                    question = questionWithCorrectAnswers.question,
                    materials = questionWithCorrectAnswers.materials.map {
                        Materials(id = it.id, text = it.text)
                    },
                    correctAnswer = questionWithCorrectAnswers.answer,
                    answerInput = AnswerInputState.SingleOption(id = null)
                )
                is QuestionWithCorrectAnswers.Categorization -> Categorization(
                    question = questionWithCorrectAnswers.question,
                    materials = questionWithCorrectAnswers.materials.map {
                        Materials(id = it.id, text = it.text)
                    },
                    correctAnswer = questionWithCorrectAnswers.categoriesOptions,
                    answerInput = AnswerInputState.CategorizedOptions.fromOptions(
                        options = questionWithCorrectAnswers.question.options
                    )
                )
                is QuestionWithCorrectAnswers.QuestionAnswerPairs -> QuestionAnswerPairs(
                    question = questionWithCorrectAnswers.question,
                    materials = questionWithCorrectAnswers.materials.map {
                        Materials(id = it.id, text = it.text)
                    },
                    answerInput = AnswerInputState.QuestionAnswerPair.fromAnswerTextList(
                        questions = questionWithCorrectAnswers.question.questionPairs,
                        answers = questionWithCorrectAnswers.question.answerPairs
                    )
                )
            }
        }
    }
}