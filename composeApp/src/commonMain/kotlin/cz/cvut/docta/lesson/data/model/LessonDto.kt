package cz.cvut.docta.lesson.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class LessonDto {
    abstract val sectionId: Int
    abstract val id: Int
    abstract val orderNum: Int
    abstract val name: String
    abstract val description: String

    @Serializable
    @SerialName("DefaultLesson")
    data class Default(
        override val sectionId: Int,
        override val id: Int,
        override val orderNum: Int,
        override val name: String,
        override val description: String,
        val type: DefaultLessonTypeDto
    ) : LessonDto()

    @Serializable
    @SerialName("StepByStepLesson")
    data class StepByStep(
        override val sectionId: Int,
        override val id: Int,
        override val orderNum: Int,
        override val name: String,
        override val description: String
    ) : LessonDto()

}