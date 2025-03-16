package cz.cvut.docta.achievement.domain.model

data class Achievement(
    val name: AchievementName,
    val title: String,
    val percentage: Double,
    val completed: Boolean
)
