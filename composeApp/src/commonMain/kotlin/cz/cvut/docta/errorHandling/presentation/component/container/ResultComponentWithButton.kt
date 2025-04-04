package cz.cvut.docta.errorHandling.presentation.component.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.button.SmallPrimaryButton
import cz.cvut.docta.core.presentation.component.button.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.container.LargePrimaryIconWithMessage
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.close_icon
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ResultComponentWithButton(
    iconRes: DrawableResource,
    iconDescription: String,
    iconGradient: List<Color>,
    title: String,
    message: String? = null,
    buttonText: String = stringResource(SharedRes.strings.close),
    buttonIconRes: DrawableResource? = Res.drawable.close_icon,
    usePrimaryButtonInstead: Boolean = false,
    onButtonClick: () -> Unit
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
        if (usePrimaryButtonInstead) {
            SmallPrimaryButton(
                text = buttonText,
                iconRes = buttonIconRes,
                onClick = onButtonClick
            )
        } else {
            SmallSecondaryButton(
                text = buttonText,
                iconRes = buttonIconRes,
                onClick = onButtonClick
            )
        }
    }
}