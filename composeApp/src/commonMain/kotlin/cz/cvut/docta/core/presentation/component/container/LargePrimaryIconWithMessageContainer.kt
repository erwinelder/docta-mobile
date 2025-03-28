package cz.cvut.docta.core.presentation.component.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun LargePrimaryIconWithMessageContainer(
    title: String,
    message: String? = null,
    iconRes: DrawableResource,
    iconDescription: String,
    gradientColor: List<Color> = DoctaColors.primaryGradient,
    filledWidth: FilledWidthByScreenType? = FilledWidthByScreenType(compact = .86f),
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        LargePrimaryIconWithMessage(
            title = title,
            message = message,
            iconRes = iconRes,
            iconDescription = iconDescription,
            gradientColor = gradientColor,
            filledWidth = filledWidth
        )
    }
}