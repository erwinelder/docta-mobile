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
fun SignInScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    email: String = "example@fel.cvut.cz",
    password: String = "_Password1"
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        SignInScreen(
            emailState = ValidatedFieldUiState(
                fieldText = email,
                validationStates = UserDataValidator.validateEmail(email).toUiStates()
            ),
            onEmailChange = {},
            passwordState = ValidatedFieldUiState(
                fieldText = password,
                validationStates = UserDataValidator.validatePassword(password).toUiStates()
            ),
            onPasswordChange = {},
            signInIsAllowed = UserDataValidator.isValidEmail(email) &&
                    UserDataValidator.isValidPassword(password),
            onSignIn = {},
            resultState = null,
            onResultReset = {},
            onNavigateToSignUpScreen = {}
        )
    }
}