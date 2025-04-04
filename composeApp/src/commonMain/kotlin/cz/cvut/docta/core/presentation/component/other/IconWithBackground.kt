package cz.cvut.docta.core.presentation.component.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun LargePrimaryIcon(
    iconRes: DrawableResource,
    gradientColor: List<Color>,
    iconDescription: String,
    iconTint: Color = DoctaColors.onPrimary,
    iconSize: Dp = 100.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(50),
                spotColor = gradientColor.first()
            )
            .clip(RoundedCornerShape(50))
            .background(
                brush = Brush.linearGradient(
                    colors = gradientColor,
                    start = Offset(0f, 240f),
                    end = Offset(240f, 0f)
                )
            )
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = iconDescription,
            tint = iconTint,
            modifier = Modifier.size(iconSize)
        )
    }
}