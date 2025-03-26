package cz.cvut.docta.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.auth.mapper.toResultState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.presentation.model.RequestState

@Preview(device = PIXEL_7_PRO)
@Composable
fun DeleteUserAccountScreenPreview(
    appTheme: AppTheme = AppTheme.Light
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        DeleteUserAccountScreen(
            onNavigateBack = {},
            onDeleteAccount = {},
            requestState = null,
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}

@Preview(device = PIXEL_7_PRO)
@Composable
fun DeleteUserAccountScreenSuccessPreview(
    appTheme: AppTheme = AppTheme.Light,
    authSuccess: AuthSuccess = AuthSuccess.UserAccountDeleted
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        DeleteUserAccountScreen(
            onNavigateBack = {},
            onDeleteAccount = {},
            requestState = RequestState.Result(resultState = authSuccess.toResultState()),
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}

@Preview(device = PIXEL_7_PRO)
@Composable
fun DeleteUserAccountScreenErrorPreview(
    appTheme: AppTheme = AppTheme.Light,
    authError: AuthError = AuthError.AccountDeletionError
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        DeleteUserAccountScreen(
            onNavigateBack = {},
            onDeleteAccount = {},
            requestState = RequestState.Result(resultState = authError.toResultState()),
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}