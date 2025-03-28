package cz.cvut.docta.lesson.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class LessonWithProgressDto {
    abstract val sectionId: Int
    abstract val id: Int
    abstract val orderNum: Int
    abstract val name: String
    abstract val description: String
    abstract val completed: Boolean

    @Serializable
    @SerialName("DefaultLesson")
    data class DefaultLesson(
        override val sectionId: Int,
        override val id: Int,
        override val orderNum: Int,
        override val name: String,
        override val description: String,
        override val completed: Boolean,
        val type: DefaultLessonTypeDto
    ) : LessonWithProgressDto()

    @Serializable
    @SerialName("StepByStepLesson")
    data class StepByStepLesson(
        override val sectionId: Int,
        override val id: Int,
        override val orderNum: Int,
        override val name: String,
        override val description: String,
        override val completed: Boolean
    ) : LessonWithProgressDto()

}