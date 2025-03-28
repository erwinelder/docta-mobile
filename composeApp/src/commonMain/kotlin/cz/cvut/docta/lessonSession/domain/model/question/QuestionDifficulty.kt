package cz.cvut.docta.lessonSession.domain.model.question

import kotlinx.serialization.Serializable

@Serializable
enum class QuestionDifficulty {
    Easy, Medium, Hard
}