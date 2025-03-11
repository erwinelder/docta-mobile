package cz.cvut.docta.lesson.data.local.model.entity_with_details

import cz.cvut.docta.lesson.data.local.model.DefaultLessonType
import cz.cvut.docta.lesson.data.local.model.LessonDifficulty
import cz.cvut.docta.lesson.data.local.model.UserLessonDetailsStats
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class LessonDetailsWithUserStats {
    abstract val sectionId: Long
    abstract val id: Long
    abstract val orderNum: Int
    abstract val name: String
    abstract val difficulty: LessonDifficulty
    abstract val statistics: UserLessonDetailsStats

    @Serializable
    @SerialName("DefaultLesson")
    data class DefaultLesson(
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val difficulty: LessonDifficulty,
        override val statistics: UserLessonDetailsStats,
        val type: DefaultLessonType,
        val matchAllTags: Boolean
    ) : LessonDetailsWithUserStats()

    @Serializable
    @SerialName("StepByStepLesson")
    data class StepByStepLesson(
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val statistics: UserLessonDetailsStats,
        override val difficulty: LessonDifficulty,
        val description: String
    ) : LessonDetailsWithUserStats()

}