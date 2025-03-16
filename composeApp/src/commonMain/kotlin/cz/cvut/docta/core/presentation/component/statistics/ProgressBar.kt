package cz.cvut.docta.core.presentation.component.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.theme.DoctaColors

@Composable
fun ProgressBar(
    percentage: Float,
    gradientColor: List<Color> = DoctaColors.primaryGradient,
    shadowColor: Color = DoctaColors.primary,
    height: Dp = 12.dp
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(brush = Brush.linearGradient(DoctaColors.glassSurfaceGradient))
                .fillMaxSize()
        )
        Spacer(
            modifier = Modifier
                .shadow(
                    elevation = height / 2,
                    spotColor = shadowColor,
                    shape = RoundedCornerShape(50)
                )
                .clip(RoundedCornerShape(50))
                .background(brush = Brush.linearGradient(gradientColor))
                .fillMaxWidth(percentage)
                .fillMaxHeight()
        )
    }
}
