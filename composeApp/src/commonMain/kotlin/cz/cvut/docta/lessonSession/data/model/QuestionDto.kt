package cz.cvut.docta.lessonSession.data.model

import cz.cvut.docta.lessonSession.domain.model.question.QuestionDifficulty
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class QuestionDto {
    abstract val id: Long

    @Serializable
    @SerialName("OpenAnswer")
    data class OpenAnswer(
        override val id: Long,
        val difficulty: QuestionDifficulty,
        val text: String
    ) : QuestionDto()

    @Serializable
    @SerialName("FillInBlanks")
    data class FillInBlanks(
        override val id: Long,
        val difficulty: QuestionDifficulty,
        val text: String
    ) : QuestionDto()

    @Serializable
    @SerialName("AnswerOptions")
    data class AnswerOptions(
        override val id: Long,
        val difficulty: QuestionDifficulty,
        val text: String,
        val options: List<AnswerTextDto>
    ) : QuestionDto()

    @Serializable
    @SerialName("QuestionAnswerPairs")
    data class QuestionAnswerPairs(
        override val id: Long,
        val difficulty: QuestionDifficulty,
        val text: String,
        val questionPairs: List<AnswerTextDto>,
        val answerPairs: List<AnswerTextDto>
    ) : QuestionDto()

    @Serializable
    @SerialName("StepByStep")
    data class StepByStep(
        override val id: Long,
        val text: String
    ) : QuestionDto()

}