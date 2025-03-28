package cz.cvut.docta.core.presentation.component.divider

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.theme.DoctaColors

@Composable
fun SmallDivider(
    modifier: Modifier = Modifier,
    filledWidth: Float = .3f,
    thickness: Dp = 1.dp,
    color: Color = DoctaColors.outlineSemiTransparent
) {
    Divider(
        modifier = modifier
            .fillMaxWidth(filledWidth)
            .clip(RoundedCornerShape(1.dp)),
        thickness = thickness,
        color = color
    )
}