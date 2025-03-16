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
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.DoctaTypography
import org.jetbrains.compose.resources.painterResource

@Composable
fun AchievementComponent(
    achievement: AchievementUiState,
    iconSize: Dp = 64.dp
) {
    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        // Wrapper for the achievement icon
        Box(modifier = Modifier.Companion.size(iconSize)) {
            Icon(
                painter = painterResource(achievement.icon),
                contentDescription = achievement.title,
                modifier = Modifier.Companion.fillMaxSize()
            )
            CircleProgressBar(
                percentage = achievement.percentage.toFloat(),
                modifier = Modifier.Companion.fillMaxSize().padding(5.dp),
                strokeSize = 5.dp,
                color = DoctaColors.yellow
            )
        }
        // Achievement title
        Text(
            text = achievement.title,
            style = DoctaTypography.normal
        )
    }
}