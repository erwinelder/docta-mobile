package cz.cvut.docta.achievement.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface AchievementScreens {

    @Serializable
    data object Achievements : AchievementScreens

    @Serializable
    data object AchievementDetails : AchievementScreens

}