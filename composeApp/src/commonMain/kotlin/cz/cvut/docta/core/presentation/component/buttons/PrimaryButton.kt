package cz.cvut.docta.core.presentation.component.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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

@Composable
fun PrimaryButton(
    text: String,
    enabled: Boolean = true,
    fontSize: TextUnit = 17.sp,
    enabledGradientColor: Pair<Color, Color> = DoctaColors.primaryGradientPair,
    onClick: () -> Unit = {}
) {
    val buttonLighterColor by animateColorAsState(
        targetValue = (if (enabled) enabledGradientColor else DoctaColors.disabledGradientPair).first,
        label = "PrimaryButton container color"
    )
    val buttonDarkerColor by animateColorAsState(
        targetValue = (if (enabled) enabledGradientColor else DoctaColors.disabledGradientPair).second,
        label = "PrimaryButton container color"
    )
    val modifier = if (WindowTypeIsCompact) {
        Modifier.fillMaxWidth(.82f)
    } else {
        Modifier.width(400.dp)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.bounceClickEffect(.97f, enabled)
    ) {
        Shadow(enabled, buttonLighterColor)
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
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = modifier
                .clip(RoundedCornerShape(25.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(buttonDarkerColor, buttonLighterColor),
                        start = Offset(75f, 210f),
                        end = Offset(95f, -10f)
                    )
                )
        ) {
            Text(
                text = text,
                fontSize = fontSize,
                fontFamily = Manrope,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Composable
private fun Shadow(enabled: Boolean, enabledColor: Color) {
    val modifier = if (WindowTypeIsCompact) {
        Modifier.fillMaxWidth(.62f)
    } else {
        Modifier.width(300.dp)
    }
    val color by animateColorAsState(
        targetValue = if (enabled) {
            enabledColor
        } else {
            DoctaColors.outline
        },
        label = "primary button shadow color"
    )

    Spacer(
        modifier = modifier
            .offset(y = -(5).dp)
            .height(20.dp)
            .shadow(
                elevation = 24.dp,
                shape = RoundedCornerShape(25.dp),
                spotColor = color,
                ambientColor = Color.Transparent
            )
    )
}