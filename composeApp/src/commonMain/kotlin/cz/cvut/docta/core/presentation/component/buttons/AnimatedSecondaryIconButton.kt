package cz.cvut.docta.core.presentation.component.buttons

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun <T> AnimatedSecondaryIconButton(
    iconState: T,
    size: Dp = 44.dp,
    onClick: () -> Unit,
    iconResProvider: (T) -> DrawableResource,
    iconDescriptionProvider: (T) -> String
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.Companion
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.Companion.linearGradient(
                    colors = DoctaColors.glassSurfaceGradient,
                    start = Offset(75f, 210f),
                    end = Offset(95f, -10f)
                )
            )
            .size(size)
    ) {
        AnimatedContent(
            targetState = iconState
        ) { targetState ->
            Icon(
                painter = painterResource(
                    iconResProvider(targetState)
                ),
                contentDescription = iconDescriptionProvider(targetState),
                tint = DoctaColors.onSurface,
                modifier = Modifier.Companion.padding(10.dp)
            )
        }
    }
}