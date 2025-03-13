package cz.cvut.docta.achievements.domain.model

data class Achievement(
    val name: AchievementName,
    val title: String,
    val percentage: Double,
    val completed: Boolean
)
