package cz.cvut.docta.achievement.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AchievementWithProgressDto(
    val id: Int,
    val name: AchievementNameDto,
    val title: String,
    val description: String,
    val percentage: Float,
    val completed: Boolean
)