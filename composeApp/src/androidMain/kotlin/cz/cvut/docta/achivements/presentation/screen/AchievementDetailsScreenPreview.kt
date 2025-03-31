package cz.cvut.docta.achivements.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.SharedRes
import cz.cvut.docta.achievement.domain.model.AchievementName
import cz.cvut.docta.achievement.presentation.screen.AchievementDetailsScreen
import cz.cvut.docta.achievement.presentation.model.AchievementUiState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.achievement_placheholder_icon

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun AchievementDetailsScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    achievement: AchievementUiState = AchievementUiState(
        name = AchievementName.FirstStep,
        title = SharedRes.strings.achievement_first_step,
        description = SharedRes.strings.achievement_description_first_step,
        percentage = 50.0f,
        icon = Res.drawable.achievement_placheholder_icon,
    )
) {
    ScreenPreviewContainer {
        AchievementDetailsScreen(
            achievement = achievement,
            onClose = {}
        )
    }
}