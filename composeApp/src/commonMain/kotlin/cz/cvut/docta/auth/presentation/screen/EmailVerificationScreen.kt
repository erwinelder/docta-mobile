package cz.cvut.docta.auth.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.buttons.SmallPrimaryButton
import cz.cvut.docta.core.presentation.component.containers.LargePrimaryIconWithMessage
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButton
import cz.cvut.docta.errorHandling.presentation.component.container.RequestStateComponent
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.email_large_icon
import docta.composeapp.generated.resources.reset_icon

@Composable
fun EmailVerificationScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    onNavigateBack: () -> Unit,
    emailVerified: Boolean,
    onCheckEmailVerification: () -> Unit,
    requestState: RequestState?,
    onCancelRequest: () -> Unit,
    onCloseResult: () -> Unit
) {
    ScreenContainerWithBackNavButton(
        screenPadding = screenPadding,
        onNavigateBack = onNavigateBack,
        backButtonText = stringResource(SharedRes.strings.email_verification),
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedContent(
            targetState = requestState,
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) { result ->
            if (result == null && !emailVerified) {
                EmailVerificationPromptedComponent(onCheck = onCheckEmailVerification)
            } else if (result != null) {
                RequestStateComponent(
                    state = result,
                    closeButtonText = stringResource(SharedRes.strings.try_again),
                    closeButtonIconRes = Res.drawable.reset_icon,
                    onCancelRequest = onCancelRequest,
                    onCloseResult = onCloseResult
                )
            }
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