package cz.cvut.docta.lesson.data.local.model

import kotlinx.serialization.Serializable

@Serializable
enum class LessonType {
    Default, StepByStep
}