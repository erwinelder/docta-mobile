package cz.cvut.docta.core.presentation.component.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.containers.GlassSurface
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.short_arrow_left_icon
import docta.composeapp.generated.resources.short_arrow_right_icon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun GlassSurfaceNavigationButton(
    text: String,
    iconRes: DrawableResource? = null,
    showRightIconInsteadOfLeft: Boolean = true,
    rightIconRes: DrawableResource = Res.drawable.short_arrow_right_icon,
    filledWidths: FilledWidthByScreenType = FilledWidthByScreenType(1f, .75f, .75f),
    padding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    fontSize: TextUnit = 20.sp,
    cornerSize: Dp = 30.dp,
    onClick: () -> Unit
) {
    GlassSurface(
        filledWidths = filledWidths,
        cornerSize = cornerSize,
        modifier = Modifier.bounceClickEffect(.98f, onClick = onClick)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            if (!showRightIconInsteadOfLeft) {
                Icon(
                    painter = painterResource(Res.drawable.short_arrow_left_icon),
                    contentDescription = "left arrow icon",
                    tint = DoctaColors.onSurface,
                    modifier = Modifier.size(24.dp)
                )
            }
            iconRes?.let {
                Image(
                    painter = painterResource(iconRes),
                    contentDescription = "$text icon",
                    modifier = Modifier.size(46.dp)
                )
            }
            Text(
                text = text,
                color = DoctaColors.onSurface,
                fontSize = fontSize,
                fontFamily = Manrope,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            if (showRightIconInsteadOfLeft) {
                Icon(
                    painter = painterResource(rightIconRes),
                    contentDescription = "right arrow icon",
                    tint = DoctaColors.onSurface,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
