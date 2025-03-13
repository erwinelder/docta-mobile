package cz.cvut.docta.achivements.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.achievements.domain.model.Achievement
import cz.cvut.docta.achievements.domain.model.AchievementName
import cz.cvut.docta.achievements.presentation.AchievementsScreen
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun AchievementsScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    achievements: List<Achievement> = listOf(
        Achievement(
            percentage = 0.0,
            title = "The first",
            name = AchievementName.Default,
            completed = false
        ),
        Achievement(
            percentage = 45.0,
            title = "The first",
            name = AchievementName.Default,
            completed = false
        ),
        Achievement(
            percentage = 20.0,
            title = "The first",
            name = AchievementName.Default,
            completed = false
        ),
        Achievement(
            percentage = 67.0,
            title = "The first",
            name = AchievementName.Default,
            completed = false
        ),
        Achievement(
            percentage = 88.0,
            title = "The first",
            name = AchievementName.Default,
            completed = false
        )
    )
) {
    ScreenPreviewContainer {
        AchievementsScreen(achievements)
    }
}