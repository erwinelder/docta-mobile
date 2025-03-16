package cz.cvut.docta.core.presentation.component.containers

import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import cz.cvut.docta.core.presentation.component.buttons.SecondaryButton
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.errorHandling.presentation.model.ResultUiState
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import kotlinx.coroutines.launch

@Composable
fun ResultBottomSheet(
    resultState: ResultUiState?,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    val titleColor = if (resultState?.isSuccessful == true) {
        DoctaColors.success
    } else {
        DoctaColors.error
    }
    val iconGradientColor = if (resultState?.isSuccessful == true) {
        DoctaColors.successGradient
    } else {
        DoctaColors.errorGradient
    }

    DoctaBottomSheet(
        visible = resultState != null,
        sheetState = sheetState,
        onDismissRequest = {
            coroutineScope.launch { sheetState.hide() }
            onDismissRequest()
        },
        dragHandle = {}
    ) {
        resultState?.let {
            /*GlanceBottomSheetContentDialog(
                title = stringResource(resultState.titleRes),
                titleColor = titleColor,
                message = stringResource(resultState.messageRes),
                iconRes = if (resultState.isSuccessful) Res.drawable.success_large_icon else
                    Res.drawable.error_large_icon,
                iconDescription = if (resultState.isSuccessful) "Success" else "Error",
                iconGradientColor = iconGradientColor
            ) {
                SecondaryButton(
                    text = stringResource(Res.string.close),
                    onClick = {
                        coroutineScope.launch { sheetState.hide() }
                        onDismissRequest()
                    }
                )
            }*/
        }
    }
}



/*
@Preview(device = Devices.PIXEL_7_PRO)
@Composable
private fun ResultBottomSheetPreview() {
    val resultState = ResultUiState(
        isSuccessful = true,
        titleRes = R.string.email_sent,
        messageRes = R.string.reset_password_email_sent
//        isSuccessful = false,
//        titleRes = R.string.oops,
//        messageRes = R.string.email_for_password_reset_error
    )

    var state by remember { mutableStateOf<ResultUiState?>(resultState) }

    PreviewContainer(appTheme = AppTheme.LightDefault) {
        SmallPrimaryButton(
            text = "Show error",
            onClick = { state = resultState }
        )
        ResultBottomSheet(
            resultState = state,
            onDismissRequest = { state = null }
        )
    }
}*/
