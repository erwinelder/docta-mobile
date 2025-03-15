package cz.cvut.docta.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.auth.presentation.model.EmailVerificationState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun EmailVerificationScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    emailVerificationState: EmailVerificationState = EmailVerificationState.NotVerified
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        EmailVerificationScreen(
            onNavigateBack = {},
            emailVerificationState = emailVerificationState,
            onCheckEmailVerification = {},
            onCancelEmailVerificationCheck = {}
        )
    }
}