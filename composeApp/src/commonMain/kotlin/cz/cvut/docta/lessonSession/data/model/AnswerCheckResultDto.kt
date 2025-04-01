package cz.cvut.docta.lessonSession.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class AnswerCheckResultDto {
    abstract val questionId: Long
    abstract val isCorrect: Boolean

    @Serializable
    @SerialName("Open")
    data class Open(
        override val questionId: Long,
        override val isCorrect: Boolean,
        val answer: String
    ) : AnswerCheckResultDto()

    @Serializable
    @SerialName("Blanks")
    data class Blanks(
        override val questionId: Long,
        override val isCorrect: Boolean,
        val answers: Map<Int, String>
    ) : AnswerCheckResultDto()

    @Serializable
    @SerialName("SingleOption")
    data class SingleOption(
        override val questionId: Long,
        override val isCorrect: Boolean,
        val id: Long
    ) : AnswerCheckResultDto()

    @Serializable
    @SerialName("CategorizedOptions")
    data class CategorizedOptions(
        override val questionId: Long,
        override val isCorrect: Boolean,
        val optionToCategory: Map<Long, Long>
    ) : AnswerCheckResultDto()

    @Serializable
    @SerialName("QuestionAnswerPairs")
    data class QuestionAnswerPairs(
        override val questionId: Long,
        override val isCorrect: Boolean
    ) : AnswerCheckResultDto()

}