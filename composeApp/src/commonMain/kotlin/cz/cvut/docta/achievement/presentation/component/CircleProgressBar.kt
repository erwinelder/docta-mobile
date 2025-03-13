package cz.cvut.docta.achievements.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Circle progression bar based on canvas
 */
@Composable
fun CircleProgressBar(
    percentage: Float,
    size: Dp = 64.dp,
    color: Color = Color.Green,
    strokeSize: Dp = 2.dp,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = Modifier
            .size(size)
            .rotate(0f)
            .then(modifier)
    ) {
        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = 360f * (percentage / 100f),
            useCenter = false,
            style = Stroke(width = strokeSize.toPx())
        )
    }
}