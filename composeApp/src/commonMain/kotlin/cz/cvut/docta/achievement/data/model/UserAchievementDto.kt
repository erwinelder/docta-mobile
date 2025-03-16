package cz.cvut.docta.achievement.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserAchievementDto(
    val id: Int,
    val userId: Int,
    val achievementId: Int,
    val percentage: Double,
    val completed: Boolean
)
