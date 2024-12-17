package cz.cvut.docta.question.domain.model

data class MultipleChoiceQuestion(
    val questionId: Long,
    val correctAnswersIds: List<Long>
)
