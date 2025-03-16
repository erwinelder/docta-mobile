package cz.cvut.docta.errorHandling.presentation.component.field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.field.FieldLabel
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState

@Composable
fun LabelWithMessagesFieldWrapper(
    state: ValidatedFieldUiState,
    labelText: String? = null,
    field: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        labelText?.let { FieldLabel(text = it) }
        field()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            state.validationStates.forEach { validationState ->
                FieldMessage(state = validationState)
            }
        }
    }
}