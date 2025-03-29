package cz.cvut.docta.lessonSession.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class QuestionDifficultyDto {
    Simple, Hard
}