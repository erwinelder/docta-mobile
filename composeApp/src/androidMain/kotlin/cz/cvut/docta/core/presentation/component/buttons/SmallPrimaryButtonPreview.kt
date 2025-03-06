package cz.cvut.docta.core.presentation.component.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.screen.PreviewContainer
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.search_icon

@Preview(device = PIXEL_7_PRO)
@Composable
private fun SmallPrimaryButtonPreview() {
    PreviewContainer(appTheme = AppTheme.Light) {
        SmallPrimaryButton(
            text = "Button",
            iconRes = Res.drawable.search_icon,
            onClick = {}
        )
    }
}