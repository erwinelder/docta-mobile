package cz.cvut.docta.lessonSession.presentation.model

import cz.cvut.docta.lessonSession.domain.model.Materials
import cz.cvut.docta.lessonSession.domain.model.question.QuestionWithMaterials
import cz.cvut.docta.lessonSession.domain.model.question.Question
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInputState

sealed class QuestionWrapper {

    data class OpenAnswer(
        val question: Question.OpenAnswer,
        val materials: List<Materials>,
        val answerInput: AnswerInputState.Open
    ) : QuestionWrapper()

    data class FillInBlanks(
        val question: Question.FillInBlanks,
        val materials: List<Materials>,
        val answerInput: AnswerInputState.Blanks
    ) : QuestionWrapper()

    data class AnswerOptions(
        val question: Question.SingleOption,
        val materials: List<Materials>,
        val answerInput: AnswerInputState.SingleOption
    ) : QuestionWrapper()

    data class Categorization(
        val question: Question.Categorization,
        val materials: List<Materials>,
        val answerInput: AnswerInputState.CategorizedOptions
    ) : QuestionWrapper()

    data class Ordering(
        val question: Question.Ordering,
        val materials: List<Materials>,
        val answerInput: AnswerInputState.OrderedOptions
    ) : QuestionWrapper()

    data class QuestionAnswerPairs(
        val question: Question.QuestionAnswerPairs,
        val materials: List<Materials>,
        val answerInput: AnswerInputState.QuestionAnswerPair
    ) : QuestionWrapper()


    companion object {

        fun fromQuestion(
            questionWithMaterials: QuestionWithMaterials
        ): QuestionWrapper {
            return when (questionWithMaterials) {
                is QuestionWithMaterials.OpenAnswer -> OpenAnswer(
                    question = questionWithMaterials.question,
                    materials = questionWithMaterials.materials,
                    answerInput = AnswerInputState.Open(answer = "")
                )
                is QuestionWithMaterials.FillInBlanks -> FillInBlanks(
                    question = questionWithMaterials.question,
                    materials = questionWithMaterials.materials,
                    answerInput = AnswerInputState.Blanks.fromText(
                        text = questionWithMaterials.question.text
                    )
                )
                is QuestionWithMaterials.SingleOption -> AnswerOptions(
                    question = questionWithMaterials.question,
                    materials = questionWithMaterials.materials,
                    answerInput = AnswerInputState.SingleOption(id = null)
                )
                is QuestionWithMaterials.Categorization -> Categorization(
                    question = questionWithMaterials.question,
                    materials = questionWithMaterials.materials,
                    answerInput = AnswerInputState.CategorizedOptions.fromOptions(
                        options = questionWithMaterials.question.options
                    )
                )
                is QuestionWithMaterials.Ordering -> Ordering(
                    question = questionWithMaterials.question,
                    materials = questionWithMaterials.materials,
                    answerInput = AnswerInputState.OrderedOptions.fromOptions(
                        options = questionWithMaterials.question.orderOptions
                    )
                )
                is QuestionWithMaterials.QuestionAnswerPairs -> QuestionAnswerPairs(
                    question = questionWithMaterials.question,
                    materials = questionWithMaterials.materials,
                    answerInput = AnswerInputState.QuestionAnswerPair.fromAnswerTextList(
                        questions = questionWithMaterials.question.questionPairs,
                        answers = questionWithMaterials.question.answerPairs
                    )
                )
            }
        }
    }
}