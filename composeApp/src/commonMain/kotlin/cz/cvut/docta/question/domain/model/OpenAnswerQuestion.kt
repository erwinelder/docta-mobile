package cz.cvut.docta.question.domain.model

data class OpenAnswerQuestion(
    val questionId: Long,
    val correctAnswersIds: List<Long>
)
