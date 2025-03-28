package cz.cvut.docta.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.auth.mapper.toResultState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.presentation.model.RequestState

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun EmailVerificationScreenPreview(
    appTheme: AppTheme = AppTheme.Light
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        EmailVerificationScreen(
            onNavigateBack = {},
            onCheckEmailVerification = {},
            requestState = null,
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun EmailVerificationScreenSuccessPreview(
    appTheme: AppTheme = AppTheme.Light,
    authSuccess: AuthSuccess = AuthSuccess.SignedUp
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        EmailVerificationScreen(
            onNavigateBack = {},
            onCheckEmailVerification = {},
            requestState = RequestState.Result(resultState = authSuccess.toResultState()),
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun EmailVerificationScreenErrorPreview(
    appTheme: AppTheme = AppTheme.Light,
    authError: AuthError = AuthError.EmailNotVerifiedYet
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        EmailVerificationScreen(
            onNavigateBack = {},
            onCheckEmailVerification = {},
            requestState = RequestState.Result(resultState = authError.toResultState()),
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}