package cz.cvut.docta.question.data.model

data class OpenAnswerQuestionEntity(
    override val id: Long,
    override val type: QuestionType,
    override val text: String,
) : QuestionEntity(id = id, type = type, text = text)
