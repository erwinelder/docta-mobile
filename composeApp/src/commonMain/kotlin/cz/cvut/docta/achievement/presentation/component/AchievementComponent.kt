package cz.cvut.docta.achievement.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.achievement.presentation.model.AchievementUiState
import cz.cvut.docta.core.presentation.component.statistics.CircleProgressBar
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.DoctaTypography
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AchievementComponent(
    achievement: AchievementUiState,
    onClick: (AchievementUiState) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.bounceClickEffect(shrinkScale = .98f) {
            onClick(achievement)
        }
    ) {
        // Wrapper for the achievement icon
        AchievementIconWithProgress(
            icon = achievement.icon,
            iconDescription = achievement.title,
            percentage = achievement.percentage,
        )
        // Achievement title
        Text(
            text = stringResource(achievement.title),
            style = DoctaTypography.normal
        )
    }
}