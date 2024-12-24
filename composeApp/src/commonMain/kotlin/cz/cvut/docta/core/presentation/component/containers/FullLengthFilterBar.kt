package cz.cvut.docta.core.presentation.component.containers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> FullLengthFilterBar(
    buttonStates: List<T>,
    onGetButtonText: @Composable (T) -> String,
    onIsButtonActive: (T) -> Boolean,
    onButtonClick: (T) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        FilterBar(
            buttonStates = buttonStates,
            onGetButtonText = onGetButtonText,
            onIsButtonActive = onIsButtonActive,
            onButtonClick = onButtonClick
        )
    }
}