package cz.cvut.docta.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.errorHandling.presentation.model.RequestState

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun EmailVerificationScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    requestState: RequestState? = null
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        EmailVerificationScreen(
            onNavigateBack = {},
            onCheckEmailVerification = {},
            requestState = requestState,
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}