package cz.cvut.docta.achievement.domain.model

data class Achievement(
    val name: AchievementName,
    val numberOfSteps: Int,
    val completedSteps: Int
)
