package cz.cvut.docta.core.presentation.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun GlassSurfaceTopBackNavButton(
    text: String,
    iconRes: DrawableResource? = null,
    padding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
    onClick: () -> Unit
) {
    GlassSurfaceNavigationButton(
        text = text,
        iconRes = iconRes,
        showRightIconInsteadOfLeft = false,
        filledWidths = FilledWidthByScreenType(.96f, .75f, .75f),
        padding = padding,
        fontSize = 21.sp,
        cornerSize = 16.dp,
        onClick = onClick
    )
}