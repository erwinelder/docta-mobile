package cz.cvut.docta.core.presentation.component.button

import androidx.compose.runtime.Composable
import cz.cvut.docta.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun BackButton(
    onClick: () -> Unit,
    text: String = stringResource(SharedRes.strings.back)
) {
    NavigationTextArrowButton(
        text = text,
        showLeftArrow = true,
        onClick = onClick
    )
}