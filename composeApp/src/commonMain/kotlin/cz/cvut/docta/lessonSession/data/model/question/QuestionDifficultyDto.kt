package cz.cvut.docta.lessonSession.data.model.question

import kotlinx.serialization.Serializable

@Serializable
enum class QuestionDifficultyDto {
    Simple, Hard
}