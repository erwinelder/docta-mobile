package cz.cvut.docta.auth.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.button.PrimaryButton
import cz.cvut.docta.core.presentation.component.container.LargePrimaryIconWithMessageContainer
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButton
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.sign_out_large_icon

@Composable
fun SignOutScreen(
    screenPadding: PaddingValues,
    onNavigateBack: () -> Unit,
    onSignOut: () -> Unit
) {
    ScreenContainerWithBackNavButton(
        screenPadding = screenPadding,
        padding = PaddingValues(top = 8.dp, bottom = 24.dp),
        onNavigateBack = onNavigateBack,
        backButtonText = stringResource(SharedRes.strings.sign_out)
    ) {
        LargePrimaryIconWithMessageContainer(
            title = stringResource(SharedRes.strings.sign_out_question),
            message = stringResource(SharedRes.strings.sign_out_confirmation),
            iconRes = Res.drawable.sign_out_large_icon,
            iconDescription = "Sign out",
            modifier = Modifier.weight(1f)
        )
        PrimaryButton(
            text = stringResource(SharedRes.strings.sign_out),
            onClick = onSignOut
        )
    }
}