package cz.cvut.docta.achievement.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.achievement.presentation.component.AchievementIconWithProgress
import cz.cvut.docta.achievement.presentation.model.AchievementUiState
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaTypography
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.close_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun AchievementDetailsScreen(
    achievement: AchievementUiState,
    onClose: () -> Unit
) {
    ScreenContainer (
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(Res.drawable.close_icon),
                contentDescription = stringResource(SharedRes.strings.close),
                modifier = Modifier.bounceClickEffect {
                    onClose()
                }
            )
        }

        Column(
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AchievementIconWithProgress(
                iconDescription = achievement.title,
                icon = achievement.icon,
                percentage = achievement.percentage,
                iconSize = 128.dp
            )
            // Title
            Text(
                text = stringResource(achievement.title),
                style = DoctaTypography.titleMedium
            )
            // Description
            Text(
                text = stringResource(achievement.description),
                style = DoctaTypography.normal
            )
        }
    }
}