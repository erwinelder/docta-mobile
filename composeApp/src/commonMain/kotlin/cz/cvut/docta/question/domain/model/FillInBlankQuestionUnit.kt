package cz.cvut.docta.question.domain.model

sealed class FillInBlankQuestionUnit {

    data class Word(val word: String) : FillInBlankQuestionUnit()

    data class BlankNumber(val number: Int) : FillInBlankQuestionUnit()

}