package cz.cvut.docta.question.data.model

data class SingleChoiceQuestionEntity(
    override val id: Long,
    override val type: QuestionType,
    override val text: String,
    val correctAnswerId: Long
) : QuestionEntity(id = id, type = type, text = text)
