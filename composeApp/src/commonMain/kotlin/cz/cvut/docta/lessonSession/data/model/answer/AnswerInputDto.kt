package cz.cvut.docta.lessonSession.data.model.answer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class AnswerInputDto {
    abstract val questionId: Long

    @Serializable
    @SerialName("Open")
    data class Open(
        override val questionId: Long,
        val answer: String
    ) : AnswerInputDto()

    @Serializable
    @SerialName("Blanks")
    data class Blanks(
        override val questionId: Long,
        val answers: Map<Int, String>
    ) : AnswerInputDto()

    @Serializable
    @SerialName("SingleOption")
    data class SingleOption(
        override val questionId: Long,
        val id: Long
    ) : AnswerInputDto()

    @Serializable
    @SerialName("CategorizedOptions")
    data class CategorizedOptions(
        override val questionId: Long,
        val optionsCategoryToMap: Map<Long, Long>
    ) : AnswerInputDto()

    @Serializable
    @SerialName("QuestionAnswerPairs")
    data class QuestionAnswerPairs(
        override val questionId: Long,
        val hadErrors: Boolean
    ) : AnswerInputDto()

}