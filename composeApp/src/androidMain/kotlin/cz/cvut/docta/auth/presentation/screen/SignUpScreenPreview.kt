package cz.cvut.docta.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.auth.domain.validation.UserDataValidator
import cz.cvut.docta.auth.mapper.toResultState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.mapper.toUiStates
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SignUpScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    name: String = "User name",
    email: String = "example@fel.cvut.cz",
    password: String = "_Password1",
    confirmPassword: String = "_Password1"
) {
    val nameState = ValidatedFieldUiState(
        fieldText = name,
        validationStates = UserDataValidator.validateName(name).toUiStates()
    )
    val emailState = ValidatedFieldUiState(
        fieldText = email,
        validationStates = UserDataValidator.validateEmail(email).toUiStates()
    )
    val passwordState = ValidatedFieldUiState(
        fieldText = password,
        validationStates = UserDataValidator.validatePassword(password).toUiStates()
    )
    val confirmPasswordState = ValidatedFieldUiState(
        fieldText = confirmPassword,
        validationStates = UserDataValidator
            .validateConfirmPassword(password, confirmPassword)
            .toUiStates()
    )

    ScreenPreviewContainer(appTheme = appTheme) {
        SignUpScreen(
            nameState = nameState,
            onNameChange = {},
            emailState = emailState,
            onEmailChange = {},
            passwordState = passwordState,
            onPasswordChange = {},
            confirmPasswordState = confirmPasswordState,
            onConfirmPasswordChange = {},
            signUpIsAllowed = nameState.isValid() && emailState.isValid() &&
                    passwordState.isValid() && confirmPasswordState.isValid(),
            onSignUp = {},
            onNavigateToSignInScreen = {},
            requestState = null,
            onCancelRequest = {},
            onErrorClose = {}
        )
    }
}

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SignUpScreenErrorPreview(
    appTheme: AppTheme = AppTheme.Light,
    authError: AuthError = AuthError.UserAlreadyExists
) {
    val nameState = ValidatedFieldUiState()
    val emailState = ValidatedFieldUiState()
    val passwordState = ValidatedFieldUiState()
    val confirmPasswordState = ValidatedFieldUiState()

    ScreenPreviewContainer(appTheme = appTheme) {
        SignUpScreen(
            nameState = nameState,
            onNameChange = {},
            emailState = emailState,
            onEmailChange = {},
            passwordState = passwordState,
            onPasswordChange = {},
            confirmPasswordState = confirmPasswordState,
            onConfirmPasswordChange = {},
            signUpIsAllowed = nameState.isValid() && emailState.isValid() &&
                    passwordState.isValid() && confirmPasswordState.isValid(),
            onSignUp = {},
            onNavigateToSignInScreen = {},
            requestState = RequestState.Result(resultState = authError.toResultState()),
            onCancelRequest = {},
            onErrorClose = {}
        )
    }
}