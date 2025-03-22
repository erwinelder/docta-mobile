package cz.cvut.docta.auth.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.presentation.model.AuthSuccessScreenType
import cz.cvut.docta.core.presentation.component.buttons.PrimaryButton
import cz.cvut.docta.core.presentation.component.containers.LargePrimaryIconWithMessageContainer
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.success_large_icon

@Composable
fun AuthSuccessScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    screenType: AuthSuccessScreenType,
    onContinueButtonClick: () -> Unit
) {
    val titleRes = when (screenType) {
        AuthSuccessScreenType.SignIn -> SharedRes.strings.welcome_back_to_docta
        AuthSuccessScreenType.SignUp -> SharedRes.strings.welcome_to_docta
        AuthSuccessScreenType.AccountDeletion -> SharedRes.strings.account_deleted
    }

    ScreenContainer(screenPadding = screenPadding) {
        LargePrimaryIconWithMessageContainer(
            title = stringResource(titleRes),
            iconRes = Res.drawable.success_large_icon,
            iconDescription = "Success",
            modifier = Modifier.weight(1f)
        )
        PrimaryButton(
            text = stringResource(SharedRes.strings.continue_),
            onClick = onContinueButtonClick
        )
    }
}