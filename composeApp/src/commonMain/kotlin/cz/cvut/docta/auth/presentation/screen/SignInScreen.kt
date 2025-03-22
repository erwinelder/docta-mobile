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
fun SignInScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    emailState: ValidatedFieldUiState,
    onEmailChange: (String) -> Unit,
    passwordState: ValidatedFieldUiState,
    onPasswordChange: (String) -> Unit,
    signInIsAllowed: Boolean,
    onSignIn: () -> Unit,
    onNavigateToSignUpScreen: () -> Unit,
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
            title = stringResource(SharedRes.strings.sign_in_to_your_docta_account),
            glassSurfaceContent = {
                GlassSurfaceContent(
                    emailState = emailState,
                    onEmailChange = onEmailChange,
                    passwordState = passwordState,
                    onPasswordChange = onPasswordChange,
                )
            },
            buttonUnderGlassSurface = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PrimaryButton(
                        text = stringResource(SharedRes.strings.sign_in),
                        enabled = signInIsAllowed,
                        onClick = onSignIn
                    )
                }
            },
            bottomButton = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SecondaryButton(
                        text = stringResource(SharedRes.strings.create_account),
                        onClick = onNavigateToSignUpScreen
                    )
                }
            }
        )
    }
}

@Composable
private fun GlassSurfaceContent(
    emailState: ValidatedFieldUiState,
    onEmailChange: (String) -> Unit,
    passwordState: ValidatedFieldUiState,
    onPasswordChange: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    GlassSurfaceContentColumnWrapper(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
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
    }
}
