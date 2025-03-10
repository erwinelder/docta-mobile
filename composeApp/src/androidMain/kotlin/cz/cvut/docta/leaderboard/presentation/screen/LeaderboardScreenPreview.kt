package cz.cvut.docta.leaderboard.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.leaderboard.domain.model.LeaderboardItem

@Composable
@Preview(device = Devices.PIXEL_7_PRO)
fun LeaderboardScreenPreview (
    appTheme: AppTheme = AppTheme.Light,
    leaders: List<LeaderboardItem> = listOf(
        LeaderboardItem("Petr pavel", 45.0, false),
        LeaderboardItem("Pavel Naplava", 34.0, true),
        LeaderboardItem("Jiri Sebek", 12.0, false),
        LeaderboardItem("This User", 10.0, false),
    )
){
    ScreenPreviewContainer (appTheme = appTheme) {
        LeaderboardScreen(leaders)
    }
}