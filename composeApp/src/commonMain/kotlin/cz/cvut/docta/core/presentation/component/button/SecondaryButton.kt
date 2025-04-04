package cz.cvut.docta.core.presentation.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.core.presentation.theme.WindowTypeIsCompact
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SecondaryButton(
    text: String,
    iconRes: DrawableResource? = null,
    enabled: Boolean = true,
    fontSize: TextUnit = 17.sp,
    enabledGradientColor: Pair<Color, Color> = DoctaColors.glassSurfaceGradientPair,
    onClick: () -> Unit = {}
) {
    val buttonLighterColor by animateColorAsState(
        targetValue = enabledGradientColor.first.copy(.5f)
    )
    val buttonDarkerColor by animateColorAsState(
        targetValue = enabledGradientColor.second.copy(.5f)
    )
    val modifier = if (WindowTypeIsCompact) {
        Modifier.fillMaxWidth(.86f)
    } else {
        Modifier.width(400.dp)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.bounceClickEffect(.98f, enabled)
    ) {
        Button(
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = DoctaColors.onPrimary,
                disabledBackgroundColor = Color.Transparent,
                disabledContentColor = DoctaColors.onPrimary,
            ),
            elevation = null,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, DoctaColors.primarySemiTransparent, RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(buttonDarkerColor, buttonLighterColor),
                        start = Offset(75f, 210f),
                        end = Offset(95f, -10f)
                    )
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                iconRes?.let {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = "$text button icon",
                        tint = DoctaColors.onSurface,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = text,
                    fontSize = fontSize,
                    color = DoctaColors.onSurface,
                    fontFamily = Manrope,
                    letterSpacing = .5.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }
    }
}