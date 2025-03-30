package cz.cvut.docta.lessonSession.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class CorrectAnswerDto {
    abstract val questionId: Long

    @Serializable
    @SerialName("OpenAnswers")
    data class OpenAnswers(
        override val questionId: Long,
        val answers: List<String>
    ) : CorrectAnswerDto()

    @Serializable
    @SerialName("Blanks")
    data class Blanks(
        override val questionId: Long,
        val blanksAnswers: Map<Int, List<String>>
    ) : CorrectAnswerDto()

    @Serializable
    @SerialName("Option")
    data class Option(
        override val questionId: Long,
        val ids: List<Long>
    ) : CorrectAnswerDto()

    @Serializable
    @SerialName("StepAnswer")
    data class StepAnswer(
        override val questionId: Long,
        val answer: String
    ) : CorrectAnswerDto()

}