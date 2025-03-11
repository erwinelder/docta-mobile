package cz.cvut.docta.lesson.data.local.model.entity_with_details

import cz.cvut.docta.lesson.data.local.model.DefaultLessonType
import cz.cvut.docta.lesson.data.local.model.LessonDifficulty
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class LessonDetails {
    abstract val sectionId: Long
    abstract val id: Long
    abstract val orderNum: Int
    abstract val name: String
    abstract val difficulty: LessonDifficulty

    @Serializable
    @SerialName("DefaultLesson")
    data class Default(
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val difficulty: LessonDifficulty,
        val type: DefaultLessonType,
        val matchAllTags: Boolean
    ) : LessonDetails()

    @Serializable
    @SerialName("StepByStepLesson")
    data class StepByStep(
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val difficulty: LessonDifficulty,
        val description: String
    ) : LessonDetails()

}