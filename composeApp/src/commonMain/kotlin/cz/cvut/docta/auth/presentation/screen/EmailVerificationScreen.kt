package cz.cvut.docta.auth.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.presentation.model.EmailVerificationState
import cz.cvut.docta.core.presentation.component.buttons.SmallPrimaryButton
import cz.cvut.docta.core.presentation.component.buttons.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.containers.LargePrimaryIconWithMessage
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButton
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.close_icon
import docta.composeapp.generated.resources.email_large_icon
import docta.composeapp.generated.resources.error_large_icon
import docta.composeapp.generated.resources.reset_icon

@Composable
fun EmailVerificationScreen(
    onNavigateBack: () -> Unit,
    emailVerificationState: EmailVerificationState,
    onCheckEmailVerification: () -> Unit,
    onCancelEmailVerificationCheck: () -> Unit
) {
    ScreenContainerWithBackNavButton(
        onBackButtonClick = onNavigateBack,
        backButtonText = stringResource(SharedRes.strings.email_verification),
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedContent(
            targetState = emailVerificationState,
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) { state ->
            when (state) {
                EmailVerificationState.Prompted -> EmailVerificationPromptedComponent(
                    onCheck = onCheckEmailVerification
                )
                EmailVerificationState.Loading -> EmailVerificationCheckLoadingComponent(
                    onCancel = onCancelEmailVerificationCheck
                )
                EmailVerificationState.NotVerified -> EmailVerificationCheckNotVerifiedComponent(
                    onTryAgain = onCheckEmailVerification
                )
                EmailVerificationState.Verified -> {}
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

@Composable
private fun EmailVerificationCheckLoadingComponent(onCancel: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(SharedRes.strings.checking_email_verification),
            color = DoctaColors.outline,
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            fontFamily = Manrope
        )
        SmallSecondaryButton(
            text = stringResource(SharedRes.strings.cancel),
            iconRes = Res.drawable.close_icon,
            onClick = onCancel
        )
    }
}

@Composable
private fun EmailVerificationCheckNotVerifiedComponent(onTryAgain: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        LargePrimaryIconWithMessage(
            title = stringResource(SharedRes.strings.not_verified),
            message = stringResource(SharedRes.strings.your_email_not_verified_description),
            iconRes = Res.drawable.error_large_icon,
            iconDescription = "Error icon",
            gradientColor = DoctaColors.errorGradient
        )
        SmallSecondaryButton(
            text = stringResource(SharedRes.strings.try_again),
            iconRes = Res.drawable.reset_icon,
            onClick = onTryAgain
        )
    }
}