package cz.cvut.docta.achivements.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.SharedRes
import cz.cvut.docta.achievement.domain.model.AchievementName
import cz.cvut.docta.achievement.presentation.screen.AchievementsScreen
import cz.cvut.docta.achievement.presentation.model.AchievementUiState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.achievement_placheholder_icon

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun AchievementsScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    achievements: List<AchievementUiState> = listOf(
        AchievementUiState(
            percentage = 100.0f,
            title = SharedRes.strings.achievement_first_step,
            name = AchievementName.FirstStep,
            icon = Res.drawable.achievement_placheholder_icon,
            description = SharedRes.strings.achievement_description_first_step,
        ),
        AchievementUiState(
            percentage = 10.0f,
            title = SharedRes.strings.achievement_knowledge_is_power,
            name = AchievementName.FirstStep,
            icon = Res.drawable.achievement_placheholder_icon,
            description = SharedRes.strings.achievement_description_first_step,
        ),
        AchievementUiState(
            percentage = 30.0f,
            title = SharedRes.strings.achievement_first_step,
            name = AchievementName.FirstStep,
            icon = Res.drawable.achievement_placheholder_icon,
            description = SharedRes.strings.achievement_description_first_step,
        ),
        AchievementUiState(
            percentage = 70.0f,
            title = SharedRes.strings.achievement_iron_will,
            name = AchievementName.FirstStep,
            icon = Res.drawable.achievement_placheholder_icon,
            description = SharedRes.strings.achievement_description_first_step,
        )
    )
) {
    ScreenPreviewContainer {
        AchievementsScreen(
            achievements = achievements,
            onAchievementClick = {}
        )
    }
}