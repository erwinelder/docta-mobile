package cz.cvut.docta.core.presentation.component.buttons

import androidx.compose.runtime.Composable
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource

@Composable
fun BackButton(
    onClick: () -> Unit,
    text: String = stringResource(Res.string.back)
) {
    NavigationTextArrowButton(
        text = text,
        showLeftArrow = true,
        onClick = onClick
    )
}