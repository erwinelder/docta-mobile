package cz.cvut.docta.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
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

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SignInScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    email: String = "example@fel.cvut.cz",
    password: String = "_Password1"
) {
    val emailState = ValidatedFieldUiState(
        fieldText = email,
        validationStates = UserDataValidator.validateEmail(email).toUiStates()
    )
    val passwordState = ValidatedFieldUiState(
        fieldText = password,
        validationStates = UserDataValidator.validateRequiredFieldIsNotEmpty(password)
            .toUiStates()
    )

    ScreenPreviewContainer(appTheme = appTheme) {
        SignInScreen(
            emailState = emailState,
            onEmailChange = {},
            passwordState = passwordState,
            onPasswordChange = {},
            signInIsAllowed = emailState.isValid() && passwordState.isValid(),
            onSignIn = {},
            onNavigateToSignUpScreen = {},
            requestState = null,
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SignInScreenSuccessPreview(
    appTheme: AppTheme = AppTheme.Light,
    authSuccess: AuthSuccess = AuthSuccess.SignedIn
) {
    val emailState = ValidatedFieldUiState()
    val passwordState = ValidatedFieldUiState()

    ScreenPreviewContainer(appTheme = appTheme) {
        SignInScreen(
            emailState = emailState,
            onEmailChange = {},
            passwordState = ValidatedFieldUiState(),
            onPasswordChange = {},
            signInIsAllowed = emailState.isValid() && passwordState.isValid(),
            onSignIn = {},
            onNavigateToSignUpScreen = {},
            requestState = RequestState.Result(resultState = authSuccess.toResultState()),
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SignInScreenErrorPreview(
    appTheme: AppTheme = AppTheme.Light,
    authError: AuthError = AuthError.WrongCredentials
) {
    val emailState = ValidatedFieldUiState()
    val passwordState = ValidatedFieldUiState()

    ScreenPreviewContainer(appTheme = appTheme) {
        SignInScreen(
            emailState = emailState,
            onEmailChange = {},
            passwordState = ValidatedFieldUiState(),
            onPasswordChange = {},
            signInIsAllowed = emailState.isValid() && passwordState.isValid(),
            onSignIn = {},
            onNavigateToSignUpScreen = {},
            requestState = RequestState.Result(resultState = authError.toResultState()),
            onCancelRequest = {},
            onSuccessClose = {},
            onErrorClose = {}
        )
    }
}