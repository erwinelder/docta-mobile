package cz.cvut.docta.errorHandling.presentation.component.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun BoxScope.RequestStateComponent(
    state: RequestState,
    onCancelRequest: (() -> Unit)? = null,
    onSuccessClose: () -> Unit = {},
    onErrorClose: () -> Unit = {}
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
                ResultStateComponent(
                    resultState = state.resultState,
                    onSuccessClose = onSuccessClose,
                    onErrorClose = onErrorClose
                )
            }
        }
    }
}

@Composable
fun ColumnScope.RequestStateComponent(
    state: RequestState,
    fill: Boolean = false,
    onCancelRequest: (() -> Unit)? = null,
    onSuccessClose: () -> Unit = {},
    onErrorClose: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .weight(1f, fill = fill)
    ) {
        when (state) {
            is RequestState.Loading -> {
                LoadingStateComponent(
                    message = stringResource(state.messageRes),
                    onCancel = onCancelRequest
                )
            }
            is RequestState.Result -> {
                ResultStateComponent(
                    resultState = state.resultState,
                    onSuccessClose = onSuccessClose,
                    onErrorClose = onErrorClose
                )
            }
        }
    }
}