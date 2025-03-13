package cz.cvut.docta.achievement.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserAchievementDto(
    val id: Long,
    val userId: Long,
    val achievementId: Long,
    val percentage: Double,
    val completed: Boolean
)
