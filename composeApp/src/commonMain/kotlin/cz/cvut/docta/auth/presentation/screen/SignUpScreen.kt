package cz.cvut.docta.auth.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.buttons.PrimaryButton
import cz.cvut.docta.core.presentation.component.buttons.SecondaryButton
import cz.cvut.docta.core.presentation.component.containers.GlassSurfaceContentColumnWrapper
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithTitleAndGlassSurface
import cz.cvut.docta.errorHandling.presentation.component.container.ScreenWithRequestState
import cz.cvut.docta.errorHandling.presentation.component.field.LargeTextFieldWithLabelAndMessages
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SignUpScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    nameState: ValidatedFieldUiState,
    onNameChange: (String) -> Unit,
    emailState: ValidatedFieldUiState,
    onEmailChange: (String) -> Unit,
    passwordState: ValidatedFieldUiState,
    onPasswordChange: (String) -> Unit,
    confirmPasswordState: ValidatedFieldUiState,
    onConfirmPasswordChange: (String) -> Unit,
    signUpIsAllowed: Boolean,
    onSignUp: () -> Unit,
    onNavigateToSignInScreen: () -> Unit,
    requestState: RequestState?,
    onCancelRequest: () -> Unit,
    onCloseResult: () -> Unit
) {
    ScreenWithRequestState(
        screenPadding = screenPadding,
        requestState = requestState,
        onCancelRequest = onCancelRequest,
        onCloseResult = onCloseResult
    ) {
        ScreenContainerWithTitleAndGlassSurface(
            screenPadding = screenPadding,
            title = stringResource(SharedRes.strings.create_your_docta_account),
            glassSurfaceContent = {
                GlassSurfaceContent(
                    nameState = nameState,
                    onNameChange = onNameChange,
                    emailState = emailState,
                    onEmailChange = onEmailChange,
                    passwordState = passwordState,
                    onPasswordChange = onPasswordChange,
                    confirmPasswordState = confirmPasswordState,
                    onConfirmPasswordChange = onConfirmPasswordChange
                )
            },
            buttonUnderGlassSurface = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PrimaryButton(
                        text = stringResource(SharedRes.strings.create_account),
                        enabled = signUpIsAllowed,
                        onClick = onSignUp
                    )
                }
            },
            bottomButton = {
                SecondaryButton(
                    text = stringResource(SharedRes.strings.sign_in),
                    onClick = onNavigateToSignInScreen
                )
            }
        )
    }
}

@Composable
private fun GlassSurfaceContent(
    nameState: ValidatedFieldUiState,
    onNameChange: (String) -> Unit,
    emailState: ValidatedFieldUiState,
    onEmailChange: (String) -> Unit,
    passwordState: ValidatedFieldUiState,
    onPasswordChange: (String) -> Unit,
    confirmPasswordState: ValidatedFieldUiState,
    onConfirmPasswordChange: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    GlassSurfaceContentColumnWrapper(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        LargeTextFieldWithLabelAndMessages(
            state = nameState,
            onValueChange = onNameChange,
            keyboardType = KeyboardType.Text,
            placeholderText = stringResource(SharedRes.strings.name),
            labelText = stringResource(SharedRes.strings.name)
        )
        LargeTextFieldWithLabelAndMessages(
            state = emailState,
            onValueChange = onEmailChange,
            keyboardType = KeyboardType.Email,
            placeholderText = stringResource(SharedRes.strings.email),
            labelText = stringResource(SharedRes.strings.email)
        )
        LargeTextFieldWithLabelAndMessages(
            state = passwordState,
            onValueChange = onPasswordChange,
            keyboardType = KeyboardType.Password,
            placeholderText = stringResource(SharedRes.strings.password),
            labelText = stringResource(SharedRes.strings.password)
        )
        LargeTextFieldWithLabelAndMessages(
            state = confirmPasswordState,
            onValueChange = onConfirmPasswordChange,
            keyboardType = KeyboardType.Password,
            placeholderText = stringResource(SharedRes.strings.password),
            labelText = stringResource(SharedRes.strings.password)
        )
    }
}
