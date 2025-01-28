package cz.cvut.docta.core.presentation.component.field

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import cz.cvut.docta.core.presentation.theme.DoctaColors

@Composable
fun TextSelectionColorsProviderWrapper(
    content: @Composable () -> Unit
) {
    val textSelectionColors = TextSelectionColors(
        handleColor = DoctaColors.primary,
        backgroundColor = DoctaColors.primary.copy(.5f),
    )

    CompositionLocalProvider(LocalTextSelectionColors provides textSelectionColors) {
        content()
    }
}