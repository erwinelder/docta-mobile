package cz.cvut.docta.lessonSession.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class QuestionDifficultyDto {
    Easy, Medium, Hard
}