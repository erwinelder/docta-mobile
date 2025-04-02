package cz.cvut.docta.errorHandling.presentation.component.screenContainer

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.errorHandling.presentation.model.RequestState

@Composable
fun AnimatedRequestStateScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    requestState: RequestState?,
    onCancelRequest: (() -> Unit)? = null,
    onSuccessClose: () -> Unit,
    onErrorClose: () -> Unit
) {
    AnimatedContent(
        targetState = requestState,
        modifier = Modifier
            .fillMaxSize()
            .padding(screenPadding)
    ) { state ->
        if (state != null) {
            RequestStateScreen(
                state = state,
                onCancelRequest = onCancelRequest,
                onSuccessClose = onSuccessClose,
                onErrorClose = onErrorClose
            )
        }
    }
}