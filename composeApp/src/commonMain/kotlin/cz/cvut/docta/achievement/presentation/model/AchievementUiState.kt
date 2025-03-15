package cz.cvut.docta.achievement.presentation.model

import cz.cvut.docta.achievement.domain.model.Achievement
import cz.cvut.docta.achievement.domain.model.AchievementName
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.achievement_placheholder_icon
import org.jetbrains.compose.resources.DrawableResource

data class AchievementUiState(
    val name: AchievementName,
    val title: String,
    val percentage: Double,
    val completed: Boolean,
    val icon: DrawableResource
) {

    companion object {

        fun fromAchievement(
            achievement: Achievement
        ): AchievementUiState {
            return AchievementUiState(
                name = achievement.name,
                title = achievement.title,
                percentage = achievement.percentage,
                completed = achievement.completed,
                icon = if (!achievement.completed) {
                    Res.drawable.achievement_placheholder_icon
                } else {
                    when (achievement.name) {
                        AchievementName.First -> Res.drawable.achievement_placheholder_icon
                        AchievementName.Second -> Res.drawable.achievement_placheholder_icon
                        AchievementName.Third -> Res.drawable.achievement_placheholder_icon
                        AchievementName.Default -> Res.drawable.achievement_placheholder_icon
                    }
                }
            )
        }

    }

}