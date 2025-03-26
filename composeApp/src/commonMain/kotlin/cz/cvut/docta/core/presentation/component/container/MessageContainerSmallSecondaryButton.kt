package cz.cvut.docta.core.presentation.component.container

import androidx.compose.runtime.Composable
import cz.cvut.docta.core.presentation.component.button.SmallSecondaryButton
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun MessageContainerSmallSecondaryButton(
    text: String,
    buttonText: String,
    buttonIconRes: DrawableResource? = null,
    onClick: () -> Unit
) {
    MessageContainer(text = text) {
        SmallSecondaryButton(
            text = buttonText,
            iconRes = buttonIconRes,
            onClick = onClick
        )
    }
}