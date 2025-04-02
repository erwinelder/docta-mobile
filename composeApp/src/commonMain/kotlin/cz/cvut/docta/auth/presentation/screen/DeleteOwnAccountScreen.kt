package cz.cvut.docta.auth.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.button.PrimaryButton
import cz.cvut.docta.core.presentation.component.container.GlassSurfaceContentColumnWrapper
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButtonTitleAndGlassSurface
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.errorHandling.presentation.component.container.AnimatedScreenWithRequestState
import cz.cvut.docta.errorHandling.presentation.component.field.LargeTextFieldWithLabelAndMessages
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun DeleteOwnAccountScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    onNavigateBack: () -> Unit,
    passwordState: ValidatedFieldUiState,
    allowDeleteAccount: Boolean,
    onPasswordChange: (String) -> Unit,
    onDeleteAccount: () -> Unit,
    requestState: RequestState?,
    onCancelRequest: () -> Unit,
    onSuccessClose: () -> Unit,
    onErrorClose: () -> Unit
) {
    AnimatedScreenWithRequestState(
        screenPadding = screenPadding,
        requestState = requestState,
        onCancelRequest = onCancelRequest,
        onSuccessClose = onSuccessClose,
        onErrorClose = onErrorClose
    ) {
        ScreenContainerWithBackNavButtonTitleAndGlassSurface(
            onNavigateBack = onNavigateBack,
            backButtonText = stringResource(SharedRes.strings.delete_account),
            title = stringResource(SharedRes.strings.delete_own_account_question),
            glassSurfaceContent = {
                val scrollState = rememberScrollState()

                GlassSurfaceContentColumnWrapper(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    LargeTextFieldWithLabelAndMessages(
                        state = passwordState,
                        onValueChange = onPasswordChange,
                        keyboardType = KeyboardType.Password,
                        placeholderText = stringResource(SharedRes.strings.password),
                        labelText = stringResource(SharedRes.strings.password),
                        imeAction = ImeAction.Done
                    )
                }
            },
            bottomButton = {
                PrimaryButton(
                    text = stringResource(SharedRes.strings.delete_account),
                    enabled = allowDeleteAccount,
                    enabledGradientColor = DoctaColors.dangerousGlassGradientPair,
                    onClick = onDeleteAccount
                )
            }
        )
    }

}