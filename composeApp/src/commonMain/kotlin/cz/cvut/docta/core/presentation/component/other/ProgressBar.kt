package cz.cvut.docta.core.presentation.component.other

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    progression: IntProgression,
    colorPair: Pair<Color, Color>,
    shadowColor: Color = colorPair.second,
    height: Dp = 16.dp
) {
    val percentage by animateFloatAsState(
        targetValue = 1f / progression.last * progression.step
    )

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(DoctaColors.glassSurfaceGradient[0])
                .fillMaxWidth()
                .fillMaxHeight()
        )
        Spacer(
            modifier = Modifier
                .shadow(
                    elevation = height / 2,
                    spotColor = shadowColor,
                    shape = RoundedCornerShape(50)
                )
                .clip(RoundedCornerShape(50))
                .background(brush = Brush.linearGradient(colorPair.toList()))
                .fillMaxWidth(percentage)
                .fillMaxHeight()
        )
    }
}