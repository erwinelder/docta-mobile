package cz.cvut.docta.core.presentation.component.other

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.PreviewContainer
import cz.cvut.docta.core.presentation.theme.DoctaColors
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.success_large_icon

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun LargePrimaryIconLightDefaultPreview() {
    PreviewContainer(appTheme = AppTheme.Light) {
        LargePrimaryIcon(
            iconRes = Res.drawable.success_large_icon,
            gradientColor = DoctaColors.primaryGradient,
            iconDescription = ""
        )
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun LargePrimaryIconDarkDefaultPreview() {

    PreviewContainer(appTheme = AppTheme.Dark) {
        LargePrimaryIcon(
            iconRes = Res.drawable.success_large_icon,
            gradientColor = DoctaColors.primaryGradient,
            iconDescription = ""
        )
    }
}