package cz.cvut.docta.question.presentation.model

sealed class QuestionBlankUnit {

    data class Word(val word: String) : QuestionBlankUnit()

    data class BlankNumber(val number: Int) : QuestionBlankUnit()


    companion object {

        fun fromText(text: String): List<QuestionBlankUnit> {
            var blankCount = 1
            val units = text
                .split(" ")
                .map { string ->
                    if (string == "___") {
                        BlankNumber(number = blankCount++)
                    } else {
                        Word(word = string)
                    }
                }

            return units
        }

    }

}