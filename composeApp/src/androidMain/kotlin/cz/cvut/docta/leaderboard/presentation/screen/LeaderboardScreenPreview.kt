package cz.cvut.docta.leaderboard.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.leaderboard.domain.model.LeaderboardItem

@Composable
@Preview(device = Devices.PIXEL_7_PRO)
fun LeaderboardScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    leaders: List<LeaderboardItem> = listOf(
        LeaderboardItem(name = "Katharine Michael", points = 2.3, isCurrentUser = false),
        LeaderboardItem(name = "Ramon Holt", points = 6.7, isCurrentUser = false),
        LeaderboardItem(name = "Cathy Berg", points = 10.11, isCurrentUser = false),
        LeaderboardItem(name = "Cameron Weeks", points = 14.15, isCurrentUser = false),
        LeaderboardItem(name = "Aimee Glass", points = 18.19, isCurrentUser = false),
    )
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        LeaderboardScreen(leaders = leaders)
    }
}