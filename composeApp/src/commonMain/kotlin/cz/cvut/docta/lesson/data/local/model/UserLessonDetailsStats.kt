package cz.cvut.docta.lesson.data.local.model

import kotlinx.serialization.Serializable

@Serializable
data class UserLessonDetailsStats(
    val isDone: Boolean
)
