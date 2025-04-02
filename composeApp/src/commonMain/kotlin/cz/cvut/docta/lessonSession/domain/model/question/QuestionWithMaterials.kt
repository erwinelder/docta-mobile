package cz.cvut.docta.lessonSession.domain.model.question

import cz.cvut.docta.lessonSession.domain.model.Materials

sealed class QuestionWithMaterials {

    data class OpenAnswer(
        val question: Question.OpenAnswer,
        val materials: List<Materials>
    ) : QuestionWithMaterials()

    data class FillInBlanks(
        val question: Question.FillInBlanks,
        val materials: List<Materials>
    ) : QuestionWithMaterials()

    data class SingleOption(
        val question: Question.SingleOption,
        val materials: List<Materials>
    ) : QuestionWithMaterials()

    data class Categorization(
        val question: Question.Categorization,
        val materials: List<Materials>
    ) : QuestionWithMaterials()

    data class Ordering(
        val question: Question.Ordering,
        val materials: List<Materials>
    ) : QuestionWithMaterials()

    data class QuestionAnswerPairs(
        val question: Question.QuestionAnswerPairs,
        val materials: List<Materials>
    ) : QuestionWithMaterials()


    fun getQuestion(): Question {
        return when (this) {
            is OpenAnswer -> question
            is FillInBlanks -> question
            is SingleOption -> question
            is Categorization -> question
            is Ordering -> question
            is QuestionAnswerPairs -> question
        }
    }

}