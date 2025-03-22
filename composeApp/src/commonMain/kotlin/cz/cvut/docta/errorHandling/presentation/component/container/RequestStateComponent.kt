package cz.cvut.docta.errorHandling.presentation.component.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.close_icon
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun RequestStateComponent(
    state: RequestState,
    closeButtonText: String = stringResource(SharedRes.strings.close),
    closeButtonIconRes: DrawableResource = Res.drawable.close_icon,
    onCancelRequest: () -> Unit,
    onCloseResult: () -> Unit
){
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
                    buttonText = closeButtonText,
                    buttonIconRes = closeButtonIconRes,
                    onClose = onCloseResult
                )
            }
        }
    }
}