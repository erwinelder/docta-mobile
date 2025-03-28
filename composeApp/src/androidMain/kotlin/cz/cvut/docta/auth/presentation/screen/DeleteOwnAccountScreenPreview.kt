package cz.cvut.docta.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.auth.domain.validation.UserDataValidator
import cz.cvut.docta.auth.mapper.toResultState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.mapper.toUiStates
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState

@Preview(device = PIXEL_7_PRO)
@Composable
fun DeleteOwnAccountScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    password: String = "Password0_"
) {
    val passwordState = ValidatedFieldUiState(
        fieldText = password,
        validationStates = UserDataValidator.validateRequiredFieldIsNotEmpty(password)
            .toUiStates()
    )

    ScreenPreviewContainer(appTheme = appTheme) {
        DeleteOwnAccountScreen(
            onNavigateBack = {},
            passwordState = passwordState,
            allowDeleteAccount = passwordState.isValid(),
            onPasswordChange = {},
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
fun DeleteOwnAccountScreenSuccessPreview(
    appTheme: AppTheme = AppTheme.Light,
    authSuccess: AuthSuccess = AuthSuccess.OwnAccountDeleted
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        DeleteOwnAccountScreen(
            onNavigateBack = {},
            passwordState = ValidatedFieldUiState(),
            allowDeleteAccount = ValidatedFieldUiState().isValid(),
            onPasswordChange = {},
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
fun DeleteOwnAccountScreenErrorPreview(
    appTheme: AppTheme = AppTheme.Light,
    authError: AuthError = AuthError.AccountDeletionError
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        DeleteOwnAccountScreen(
            onNavigateBack = {},
            passwordState = ValidatedFieldUiState(),
            allowDeleteAccount = ValidatedFieldUiState().isValid(),
            onPasswordChange = {},
            onDeleteAccount = {},
            requestState = RequestState.Result(resultState = authError.toResultState()),
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}