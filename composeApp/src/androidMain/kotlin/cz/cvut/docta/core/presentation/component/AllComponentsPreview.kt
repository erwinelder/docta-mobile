package cz.cvut.docta.core.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.component.buttons.PrimaryButton
import cz.cvut.docta.core.presentation.component.buttons.SmallPrimaryButton
import cz.cvut.docta.core.presentation.component.buttons.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.other.GreetingsMessage
import cz.cvut.docta.core.presentation.preview.PreviewWrapper
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.search_icon


private val appTheme = AppTheme.Light


@Preview()
@Composable
private fun AllComponentsPreview() {
    PreviewWrapper(appTheme = appTheme) {

        GreetingsMessage()

        PrimaryButton(text = "Primary Button")

        SmallPrimaryButton(
            text = "Small Primary Button",
            iconRes = Res.drawable.search_icon
        )

        SmallSecondaryButton(
            text = "Small Secondary Button",
            iconRes = Res.drawable.search_icon
        )

    }
}
