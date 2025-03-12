package cz.cvut.docta.achivements.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.achievements.domain.model.Achievement
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
            completed = false
        ),
        Achievement(
            percentage = 10.0,
            title = "The first",
            completed = false
        ),
        Achievement(
            percentage = 50.0,
            title = "The first",
            completed = false
        ),
        Achievement(
            percentage = 0.0,
            title = "The first",
            completed = false
        ),
        Achievement(
            percentage = 10.0,
            title = "Ha ha",
            completed = false
        ),
        Achievement(
            percentage = 50.0,
            title = "You are the Best!",
            completed = false
        ),
        Achievement(
            percentage = 80.0,
            title = "The first",
            completed = false
        ),
        Achievement(
            percentage = 94.0,
            title = "Get my money",
            completed = false
        ),
        Achievement(
            percentage = 92.7,
            title = "Boss of the gym",
            completed = false
        ),
        Achievement(
            percentage = 100.0,
            title = "The first",
            completed = false
        )
    )
) {
    ScreenPreviewContainer {
        AchievementsScreen(achievements)
    }
}