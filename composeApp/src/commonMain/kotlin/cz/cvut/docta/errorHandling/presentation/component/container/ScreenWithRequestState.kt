package cz.cvut.docta.errorHandling.presentation.component.container

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.close_icon
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ScreenWithRequestState(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    requestState: RequestState?,
    closeButtonText: String = stringResource(SharedRes.strings.close),
    closeButtonIconRes: DrawableResource = Res.drawable.close_icon,
    onCancelRequest: () -> Unit,
    onCloseResult: () -> Unit,
    screenContent: @Composable () -> Unit
) {
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
                closeButtonText = closeButtonText,
                closeButtonIconRes = closeButtonIconRes,
                onCancelRequest = onCancelRequest,
                onCloseResult = onCloseResult
            )
        }
    }
}