package cz.cvut.docta.errorHandling.presentation.component.screenContainer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.cvut.docta.errorHandling.presentation.component.container.LoadingStateComponent
import cz.cvut.docta.errorHandling.presentation.component.container.ResultStateComponentWithButton
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun RequestStateScreen(
    state: RequestState,
    onCancelRequest: () -> Unit,
    onSuccessClose: () -> Unit,
    onErrorClose: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            is RequestState.Loading -> {
                LoadingStateComponent(
                    message = stringResource(state.messageRes),
                    onCancel = onCancelRequest
                )
            }
            is RequestState.Result -> {
                ResultStateComponentWithButton(
                    resultState = state.resultState,
                    onSuccessClose = onSuccessClose,
                    onErrorClose = onErrorClose
                )
            }
        }
    }
}