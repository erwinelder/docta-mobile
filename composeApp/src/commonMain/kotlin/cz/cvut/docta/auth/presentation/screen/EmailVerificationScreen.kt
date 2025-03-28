package cz.cvut.docta.auth.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.button.SmallPrimaryButton
import cz.cvut.docta.core.presentation.component.container.LargePrimaryIconWithMessage
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButton
import cz.cvut.docta.errorHandling.presentation.component.container.ScreenWithRequestState
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.email_large_icon

@Composable
fun EmailVerificationScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    onNavigateBack: () -> Unit,
    onCheckEmailVerification: () -> Unit,
    requestState: RequestState?,
    onCancelRequest: () -> Unit,
    onSuccessClose: () -> Unit,
    onErrorClose: () -> Unit
) {
    ScreenWithRequestState(
        screenPadding = screenPadding,
        requestState = requestState,
        onCancelRequest = onCancelRequest,
        onSuccessClose = onSuccessClose,
        onErrorClose = onErrorClose
    ) {
        ScreenContainerWithBackNavButton(
            verticalArrangement = Arrangement.Center,
            onNavigateBack = onNavigateBack,
            backButtonText = stringResource(SharedRes.strings.email_verification)
        ) {
            EmailVerificationPromptedComponent(onCheck = onCheckEmailVerification)
        }
    }
}

@Composable
private fun EmailVerificationPromptedComponent(onCheck: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        LargePrimaryIconWithMessage(
            title = stringResource(SharedRes.strings.email_sent),
            message = stringResource(SharedRes.strings.sign_up_email_verification_sent_description),
            iconRes = Res.drawable.email_large_icon,
            iconDescription = "Email sent icon"
        )
        SmallPrimaryButton(
            text = stringResource(SharedRes.strings.check),
            onClick = onCheck
        )
    }
}