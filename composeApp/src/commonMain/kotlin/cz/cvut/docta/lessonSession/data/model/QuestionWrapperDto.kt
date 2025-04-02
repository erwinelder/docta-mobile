package cz.cvut.docta.lessonSession.data.model

import cz.cvut.docta.lessonSession.data.model.question.QuestionDto
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class QuestionWrapperDto {

    @Serializable
    @SerialName("OpenAnswer")
    data class OpenAnswer(
        val question: QuestionDto.OpenAnswer,
        val materials: List<MaterialsDto>
    ) : QuestionWrapperDto()

    @Serializable
    @SerialName("FillInBlanks")
    data class FillInBlanks(
        val question: QuestionDto.FillInBlanks,
        val materials: List<MaterialsDto>
    ) : QuestionWrapperDto()

    @Serializable
    @SerialName("SingleOption")
    data class SingleOption(
        val question: QuestionDto.SingleOption,
        val materials: List<MaterialsDto>
    ) : QuestionWrapperDto()

    @Serializable
    @SerialName("Ordering")
    data class Ordering(
        val question: QuestionDto.Ordering,
        val materials: List<MaterialsDto>
    ) : QuestionWrapperDto()

    @Serializable
    @SerialName("QuestionAnswerPairs")
    data class QuestionAnswerPairs(
        val question: QuestionDto.QuestionAnswerPairs,
        val materials: List<MaterialsDto>
    ) : QuestionWrapperDto()

}