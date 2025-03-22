package cz.cvut.docta.errorHandling.presentation.component.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.buttons.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.containers.LargePrimaryIconWithMessage
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.close_icon
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ResultComponent(
    iconRes: DrawableResource,
    iconDescription: String,
    iconGradient: List<Color>,
    title: String,
    message: String? = null,
    buttonText: String = stringResource(SharedRes.strings.close),
    buttonIconRes: DrawableResource = Res.drawable.close_icon,
    onClose: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        LargePrimaryIconWithMessage(
            title = title,
            message = message,
            iconRes = iconRes,
            iconDescription = iconDescription,
            gradientColor = iconGradient
        )
        SmallSecondaryButton(
            text = buttonText,
            iconRes = buttonIconRes,
            onClick = onClose
        )
    }
}