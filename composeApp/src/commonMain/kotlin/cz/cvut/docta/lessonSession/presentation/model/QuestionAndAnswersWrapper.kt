package cz.cvut.docta.lessonSession.presentation.model

import cz.cvut.docta.lessonSession.domain.model.answer.CorrectAnswer
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInput
import cz.cvut.docta.lessonSession.domain.model.question.Question
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCorrectAnswers

sealed class QuestionAndAnswersWrapper {

    data class OpenAnswer(
        val question: Question.OpenAnswer,
        val correctAnswer: CorrectAnswer.OpenAnswers,
        val answerInput: AnswerInput.Open
    ) : QuestionAndAnswersWrapper()

    data class FillInBlanks(
        val question: Question.FillInBlanks,
        val correctAnswer: CorrectAnswer.Blanks,
        val answerInput: AnswerInput.Blanks
    ) : QuestionAndAnswersWrapper()

    data class AnswerOptions(
        val question: Question.AnswerOptions,
        val correctAnswer: CorrectAnswer.Option,
        val answerInput: AnswerInput.Option
    ) : QuestionAndAnswersWrapper()

    data class QuestionAnswerPairs(
        val question: Question.QuestionAnswerPairs,
        val answerInput: AnswerInput.QuestionAnswerPair
    ) : QuestionAndAnswersWrapper()

    data class StepByStep(
        val question: Question.StepByStep,
        val correctAnswer: CorrectAnswer.StepAnswer,
        val answerInput: AnswerInput.Step
    ) : QuestionAndAnswersWrapper()


    companion object {

        fun fromQuestion(
            questionWithCorrectAnswers: QuestionWithCorrectAnswers
        ): QuestionAndAnswersWrapper {
            return when (questionWithCorrectAnswers) {
                is QuestionWithCorrectAnswers.OpenAnswer -> OpenAnswer(
                    question = questionWithCorrectAnswers.question,
                    correctAnswer = questionWithCorrectAnswers.answers,
                    answerInput = AnswerInput.Open(answer = "")
                )
                is QuestionWithCorrectAnswers.FillInBlanks -> FillInBlanks(
                    question = questionWithCorrectAnswers.question,
                    correctAnswer = questionWithCorrectAnswers.blanksAnswers,
                    answerInput = AnswerInput.Blanks.fromBlankNumbers(
                        blanksNumbers = questionWithCorrectAnswers.blanksAnswers.getBlankNumbers()
                    )
                )
                is QuestionWithCorrectAnswers.AnswerOptions -> AnswerOptions(
                    question = questionWithCorrectAnswers.question,
                    correctAnswer = questionWithCorrectAnswers.answer,
                    answerInput = AnswerInput.Option(id = null)
                )
                is QuestionWithCorrectAnswers.QuestionAnswerPairs -> QuestionAnswerPairs(
                    question = questionWithCorrectAnswers.question,
                    answerInput = AnswerInput.QuestionAnswerPair.fromAnswerTextList(
                        questions = questionWithCorrectAnswers.question.questionPairs,
                        answers = questionWithCorrectAnswers.question.answerPairs
                    )
                )
                is QuestionWithCorrectAnswers.StepByStep -> StepByStep(
                    question = questionWithCorrectAnswers.question,
                    correctAnswer = questionWithCorrectAnswers.answer,
                    answerInput = AnswerInput.Step(answer = "")
                )
            }
        }

    }

}