package cz.cvut.docta.achievement.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AchievementDto(
    val id: Long,
    val name: AchievementNameDto,
    val title: String,
    val description: String,
)
