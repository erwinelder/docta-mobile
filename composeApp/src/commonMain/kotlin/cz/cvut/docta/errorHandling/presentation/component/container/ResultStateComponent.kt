package cz.cvut.docta.errorHandling.presentation.component.container

import androidx.compose.runtime.Composable
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.errorHandling.presentation.model.ResultState
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.error_large_icon
import docta.composeapp.generated.resources.success_large_icon

@Composable
fun ResultStateComponent(
    resultState: ResultState,
    onSuccessClose: () -> Unit,
    onErrorClose: () -> Unit
) {
    val iconRes = if (resultState.isSuccessful)
        Res.drawable.success_large_icon else Res.drawable.error_large_icon
    val iconDescription = if (resultState.isSuccessful) "Success icon" else "Error icon"
    val iconGradient = if (resultState.isSuccessful)
        DoctaColors.primaryGradient else DoctaColors.errorGradient

    ResultComponent(
        iconRes = iconRes,
        iconDescription = iconDescription,
        iconGradient = iconGradient,
        title = stringResource(resultState.titleRes),
        message = stringResource(resultState.messageRes),
        buttonText = stringResource(resultState.buttonTextRes),
        buttonIconRes = resultState.buttonIconRes,
        usePrimaryButtonInstead = resultState.isSuccessful,
        onButtonClick = {
            if (resultState.isSuccessful) onSuccessClose() else onErrorClose()
        }
    )
}