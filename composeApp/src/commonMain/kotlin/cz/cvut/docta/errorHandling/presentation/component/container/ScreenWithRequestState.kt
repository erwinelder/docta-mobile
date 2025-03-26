package cz.cvut.docta.errorHandling.presentation.component.container

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.navigation.SetBackHandler
import cz.cvut.docta.errorHandling.presentation.model.RequestState

@Composable
fun ScreenWithRequestState(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    requestState: RequestState?,
    onCancelRequest: () -> Unit,
    onSuccessClose: () -> Unit = {},
    onErrorClose: () -> Unit,
    screenContent: @Composable () -> Unit
) {
    SetBackHandler(enabled = requestState != null) {}

    AnimatedContent(
        targetState = requestState,
        modifier = Modifier
            .fillMaxSize()
            .padding(screenPadding)
    ) { result ->
        if (result == null) {
            screenContent()
        } else {
            RequestStateComponent(
                state = result,
                onCancelRequest = onCancelRequest,
                onSuccessClose = onSuccessClose,
                onErrorClose = onErrorClose
            )
        }
    }
}