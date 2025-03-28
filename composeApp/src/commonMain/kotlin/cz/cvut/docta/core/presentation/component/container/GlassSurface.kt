package cz.cvut.docta.core.presentation.component.container

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors

@Composable
fun GlassSurface(
    modifier: Modifier = Modifier,
    filledWidths: FilledWidthByScreenType? = FilledWidthByScreenType(),
    gradientColor: List<Color> = DoctaColors.glassSurfaceGradient,
    cornerSize: Dp = 32.dp,
    borderSize: Dp = 2.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .run {
                filledWidths?.let { fillMaxWidth(it.getByType(CurrWindowType)) } ?: this
            }
            .clip(RoundedCornerShape(cornerSize))
            .background(
                brush = Brush.linearGradient(
                    colors = gradientColor,
                    start = Offset(0f, 1400f),
                    end = Offset(600f, 0f)
                )
            )
            .border(
                width = borderSize,
                color = DoctaColors.glassSurfaceBorder,
                shape = RoundedCornerShape(cornerSize)
            )
            .padding(borderSize)
    ) {
        content()
    }
}
