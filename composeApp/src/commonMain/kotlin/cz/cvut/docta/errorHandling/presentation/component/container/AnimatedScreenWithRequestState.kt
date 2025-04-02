package cz.cvut.docta.errorHandling.presentation.component.container

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.navigation.SetBackHandler
import cz.cvut.docta.errorHandling.presentation.component.screenContainer.RequestStateScreen
import cz.cvut.docta.errorHandling.presentation.model.RequestState

@Composable
fun AnimatedScreenWithRequestState(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    requestState: RequestState?,
    onCancelRequest: (() -> Unit)? = null,
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
    ) { state ->
        if (state == null) {
            screenContent()
        } else {
            RequestStateScreen(
                state = state,
                onCancelRequest = onCancelRequest,
                onSuccessClose = onSuccessClose,
                onErrorClose = onErrorClose
            )
        }
    }
}