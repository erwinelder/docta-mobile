package cz.cvut.docta.achivements.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.achievement.domain.model.Achievement
import cz.cvut.docta.achievement.domain.model.AchievementName
import cz.cvut.docta.achievement.presentation.AchievementsScreen
import cz.cvut.docta.achievement.presentation.model.AchievementUiState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.achievement_placheholder_icon

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun AchievementsScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    achievements: List<AchievementUiState> = listOf(
        AchievementUiState(
            percentage = 0.0,
            title = "The first",
            name = AchievementName.Default,
            completed = false,
            icon = Res.drawable.achievement_placheholder_icon
        ),
        AchievementUiState(
            percentage = 30.0,
            title = "The first",
            name = AchievementName.Default,
            completed = false,
            icon = Res.drawable.achievement_placheholder_icon
        ),
        AchievementUiState(
            percentage = 30.0,
            title = "The first",
            name = AchievementName.Default,
            completed = false,
            icon = Res.drawable.achievement_placheholder_icon
        ),
        AchievementUiState(
            percentage = 50.0,
            title = "The first",
            name = AchievementName.Default,
            completed = false,
            icon = Res.drawable.achievement_placheholder_icon
        )
    )
) {
    ScreenPreviewContainer {
        AchievementsScreen(achievements = achievements)
    }
}