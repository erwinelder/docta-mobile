package cz.cvut.docta.core.presentation.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun GlassSurfaceTopBackNavButton(
    text: String,
    iconRes: DrawableResource? = null,
    padding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    GlassSurfaceNavigationButton(
        text = text,
        iconRes = iconRes,
        showRightIconInsteadOfLeft = false,
        filledWidths = FilledWidthByScreenType(.96f, .75f, .75f),
        padding = padding,
        fontSize = 20.sp,
        cornerSize = 20.dp,
        modifier = modifier,
        onClick = onClick
    )
}