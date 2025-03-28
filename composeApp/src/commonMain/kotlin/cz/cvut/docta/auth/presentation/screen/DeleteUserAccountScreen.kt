package cz.cvut.docta.auth.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.button.PrimaryButton
import cz.cvut.docta.core.presentation.component.container.LargePrimaryIconWithMessageContainer
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButton
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.errorHandling.presentation.component.container.ScreenWithRequestState
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.delete_account_large_icon

@Composable
fun DeleteUserAccountScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    onNavigateBack: () -> Unit,
    onDeleteAccount: () -> Unit,
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
            padding = PaddingValues(top = 8.dp, bottom = 24.dp),
            onNavigateBack = onNavigateBack,
            backButtonText = stringResource(SharedRes.strings.delete_account)
        ) {
            LargePrimaryIconWithMessageContainer(
                title = stringResource(SharedRes.strings.delete_user_account_question),
                message = stringResource(SharedRes.strings.delete_user_account_confirmation),
                iconRes = Res.drawable.delete_account_large_icon,
                iconDescription = "Delete account",
                gradientColor = DoctaColors.dangerousGlassGradient,
                modifier = Modifier.weight(1f)
            )
            PrimaryButton(
                text = stringResource(SharedRes.strings.delete_account),
                enabledGradientColor = DoctaColors.dangerousGlassGradientPair,
                onClick = onDeleteAccount
            )
        }
    }

}