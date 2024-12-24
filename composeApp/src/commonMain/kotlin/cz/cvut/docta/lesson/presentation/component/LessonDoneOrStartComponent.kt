package cz.cvut.docta.lesson.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.long_right_arrow_icon
import docta.composeapp.generated.resources.reset_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun LessonDoneOrStartComponent(
    isDone: Boolean,
    verticalPadding: Dp = 24.dp
) {
    val gradientColors = if (isDone) DoctaColors.primaryGradient else
        DoctaColors.outlineBackgroundGradient
    val iconColor = if (isDone) DoctaColors.onPrimary else DoctaColors.onSurface
    val iconResAndDescription = if (isDone) Res.drawable.reset_icon to "do lesson again icon" else
        Res.drawable.long_right_arrow_icon to "start lesson icon"

    Box(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = gradientColors,
                    start = Offset(75f, 210f),
                    end = Offset(95f, -10f)
                )
            )
            .padding(horizontal = 16.dp, vertical = verticalPadding)
    ) {
        Icon(
            painter = painterResource(iconResAndDescription.first),
            contentDescription = iconResAndDescription.second,
            tint = iconColor,
            modifier = Modifier.size(26.dp)
        )
    }
}