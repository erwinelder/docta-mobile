package cz.cvut.docta.lesson.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class LessonDto {
    abstract val sectionId: Long
    abstract val id: Long
    abstract val orderNum: Int
    abstract val name: String
    abstract val difficulty: LessonDifficultyDto

    @Serializable
    @SerialName("DefaultLesson")
    data class Default(
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val difficulty: LessonDifficultyDto,
        val type: DefaultLessonTypeDto,
        val matchAllTags: Boolean
    ) : LessonDto()

    @Serializable
    @SerialName("StepByStepLesson")
    data class StepByStep(
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val difficulty: LessonDifficultyDto,
        val description: String
    ) : LessonDto()

}