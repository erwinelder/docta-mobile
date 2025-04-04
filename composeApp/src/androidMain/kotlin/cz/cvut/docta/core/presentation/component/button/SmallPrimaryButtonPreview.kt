package cz.cvut.docta.core.presentation.component.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.PreviewContainer
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.search_icon

@Preview(device = PIXEL_7_PRO)
@Composable
private fun SmallPrimaryButtonPreview(
    appTheme: AppTheme = AppTheme.Light
) {
    PreviewContainer(appTheme = appTheme) {
        SmallPrimaryButton(
            text = "Button",
            iconRes = Res.drawable.search_icon,
            onClick = {}
        )
    }
}