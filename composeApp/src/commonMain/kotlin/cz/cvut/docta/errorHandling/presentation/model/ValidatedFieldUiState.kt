package cz.cvut.docta.errorHandling.presentation.model

import androidx.compose.runtime.Stable

@Stable
data class ValidatedFieldUiState(
    val fieldText: String = "",
    val validationStates: List<ValidationUiState> = emptyList()
) {

    fun isValid(): Boolean {
        return validationStates.all { it.isValid }
    }

}