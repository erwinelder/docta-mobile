package cz.cvut.docta.errorHandling.presentation.component.container

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cz.cvut.docta.core.presentation.component.container.LargePrimaryIconWithMessage
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ResultComponent(
    iconRes: DrawableResource,
    iconDescription: String,
    iconGradient: List<Color>,
    title: String,
    message: String? = null
) {
    LargePrimaryIconWithMessage(
        title = title,
        message = message,
        iconRes = iconRes,
        iconDescription = iconDescription,
        gradientColor = iconGradient
    )
}