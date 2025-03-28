package cz.cvut.docta.achievement.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AchievementDto(
    val achievementName: String,
    val completedSteps: Int,
    val numberOfSteps: Int
)
