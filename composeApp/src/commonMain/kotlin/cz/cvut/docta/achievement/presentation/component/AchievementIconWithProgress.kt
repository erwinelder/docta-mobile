package cz.cvut.docta.achievement.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.statistics.CircleProgressBar
import cz.cvut.docta.core.presentation.theme.DoctaColors
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AchievementIconWithProgress(
    iconDescription: StringResource,
    icon: DrawableResource,
    percentage: Float,
    iconSize: Dp = 128.dp,
    iconPadding: PaddingValues = PaddingValues(5.dp)
) {
    // Wrapper for the achievement icon
    Box(modifier = Modifier.size(iconSize)) {
        Icon(
            painter = painterResource(icon),
            contentDescription = stringResource(iconDescription),
            modifier = Modifier
                .padding(iconPadding)
                .fillMaxSize()
        )
        CircleProgressBar(
            percentage = percentage,
            size = iconSize,
            modifier = Modifier.padding(5.dp),
            strokeSize = 5.dp,
            color = DoctaColors.yellow
        )
    }
}