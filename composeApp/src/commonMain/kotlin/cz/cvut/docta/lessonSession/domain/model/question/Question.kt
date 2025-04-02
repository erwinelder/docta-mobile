package cz.cvut.docta.lessonSession.domain.model.question

import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText

sealed class Question(
    open val id: Long,
    open val difficulty: QuestionDifficulty
) {

    data class OpenAnswer(
        override val id: Long,
        override val difficulty: QuestionDifficulty,
        val text: String
    ) : Question(id, difficulty)

    data class FillInBlanks(
        override val id: Long,
        override val difficulty: QuestionDifficulty,
        val text: String
    ) : Question(id, difficulty)

    data class SingleOption(
        override val id: Long,
        override val difficulty: QuestionDifficulty,
        val text: String,
        val options: List<AnswerText>
    ) : Question(id, difficulty)

    data class Categorization(
        override val id: Long,
        override val difficulty: QuestionDifficulty,
        val text: String,
        val options: List<AnswerText>,
        val categories: List<AnswerText>
    ): Question(id, difficulty)

    data class Ordering(
        override val id: Long,
        override val difficulty: QuestionDifficulty,
        val text: String,
        val orderOptions: List<AnswerText>
    ): Question(id, difficulty)

    data class QuestionAnswerPairs(
        override val id: Long,
        override val difficulty: QuestionDifficulty,
        val text: String,
        val questionPairs: List<AnswerText>,
        val answerPairs: List<AnswerText>
    ) : Question(id, difficulty)

}