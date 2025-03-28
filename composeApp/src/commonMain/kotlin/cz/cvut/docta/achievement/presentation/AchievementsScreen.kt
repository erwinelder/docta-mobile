package cz.cvut.docta.achievement.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.achievement.presentation.component.AchievementComponent
import cz.cvut.docta.achievement.presentation.model.AchievementUiState
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.DoctaTypography
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AchievementsScreen(
    achievements: List<AchievementUiState>
) {
    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        // Screen title
        Text(
            text = stringResource(SharedRes.strings.achievements_screen_title),
            style = DoctaTypography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
        )

        // Achievements list
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(items = achievements) { achievement ->
                AchievementComponent(
                    achievement = achievement
                )
            }
        }
    }
}