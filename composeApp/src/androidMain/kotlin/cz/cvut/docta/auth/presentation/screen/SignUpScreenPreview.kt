package cz.cvut.docta.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.auth.domain.validation.UserDataValidator
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.errorHandling.mapper.toUiStates
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SignUpScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    name: String = "John Doe",
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
            signUpIsAllowed = UserDataValidator.isValidEmail(email) &&
                    UserDataValidator.isValidPassword(password) &&
                    password == confirmPassword,
            onSignUp = {},
            onNavigateToSignInScreen = {},
            requestState = null,
            onCancelRequest = {},
            onCloseResult = {}
        )
    }
}