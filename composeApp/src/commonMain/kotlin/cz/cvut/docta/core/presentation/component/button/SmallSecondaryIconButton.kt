package cz.cvut.docta.core.presentation.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SmallSecondaryIconButton(
    iconRes: DrawableResource?,
    enabled: Boolean = true,
    enabledGradientColor: Pair<Color, Color> = DoctaColors.glassSurfaceGradientPair,
    alpha: Float = 0.5f,
    contentColor: Color = DoctaColors.onSurface,
    borderColor: Color = DoctaColors.primarySemiTransparent,
    onClick: () -> Unit = {}
) {
    val buttonLighterColor by animateColorAsState(
        targetValue = enabledGradientColor.first.copy(alpha = alpha),
        label = "lighterColor"
    )
    val buttonDarkerColor by animateColorAsState(
        targetValue = enabledGradientColor.second.copy(alpha = alpha),
        label = "darkerColor"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(48.dp)
            .bounceClickEffect(.98f, enabled)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(buttonLighterColor, buttonDarkerColor),
                    start = Offset(75f, 210f),
                    end = Offset(95f, -10f)
                )
            )
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .clickable(enabled = enabled, onClick = onClick)
    ) {
        iconRes?.let {
            Icon(
                painter = painterResource(it),
                contentDescription = "icon button",
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
