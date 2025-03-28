package cz.cvut.docta.core.presentation.component.container

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType

@Composable
fun GlassSurfaceWithAdaptiveColor(
    gradientColorsPair: Pair<Color, Color>,
    modifier: Modifier = Modifier,
    filledWidths: FilledWidthByScreenType = FilledWidthByScreenType(),
    cornerSize: Dp = 32.dp,
    borderSize: Dp = 2.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val firstColor by animateColorAsState(targetValue = gradientColorsPair.first)
    val secondColor by animateColorAsState(targetValue = gradientColorsPair.second)

    GlassSurface(
        modifier = modifier,
        filledWidths = filledWidths,
        gradientColor = listOf(firstColor, secondColor),
        cornerSize = cornerSize,
        borderSize = borderSize,
        content = content
    )
}