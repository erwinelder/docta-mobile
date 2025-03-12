package cz.cvut.docta.achievements.domain.model

data class Achievement(
    val percentage: Double,
    val name: AchievementName? = AchievementName.Default,
    val title: String,
    val completed: Boolean
)
