package cz.cvut.docta.core.presentation.component.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.PreviewContainer

@Preview(device = PIXEL_7_PRO)
@Composable
fun PrimaryButtonPreview(
    appTheme: AppTheme = AppTheme.Light
) {
    PreviewContainer(appTheme = appTheme) {
        PrimaryButton(
            text = "Button",
            onClick = {}
        )
    }
}