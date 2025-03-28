package cz.cvut.docta.lessonSession.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class SessionOptionsDto {
    abstract val courseCode: String
    abstract val lessonId: Int

    @Serializable
    @SerialName("Default")
    data class Default(
        override val courseCode: String,
        override val lessonId: Int
    ) : SessionOptionsDto()

    @Serializable
    @SerialName("StepByStep")
    data class StepByStep(
        override val courseCode: String,
        override val lessonId: Int
    ) : SessionOptionsDto()

}