package cz.cvut.docta.core.presentation.component.container

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.PreviewContainer
import cz.cvut.docta.core.presentation.theme.DoctaColors
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.success_large_icon

@Preview(device = PIXEL_7_PRO)
@Composable
fun LargePrimaryIconWithMessagePreview(
    appTheme: AppTheme = AppTheme.Light
) {
    PreviewContainer(appTheme = appTheme) {
        LargePrimaryIconWithMessage(
            title = "Success",
            message = "You have successfully completed the action.",
            iconRes = Res.drawable.success_large_icon,
            iconDescription = "Success",
            gradientColor = DoctaColors.primaryGradient
        )
    }
}