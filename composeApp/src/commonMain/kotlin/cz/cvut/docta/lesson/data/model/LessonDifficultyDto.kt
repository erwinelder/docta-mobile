package cz.cvut.docta.lesson.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class LessonDifficultyDto {
    Easy, Medium, Hard
}