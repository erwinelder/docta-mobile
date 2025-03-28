package cz.cvut.docta.errorHandling.presentation.component.field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import cz.cvut.docta.errorHandling.presentation.model.ValidationUiState

@Composable
fun FieldValidationMessages(validationStates: List<ValidationUiState>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        validationStates.forEach { validationState ->
            FieldValidationMessage(state = validationState)
        }
    }
}