package cz.cvut.docta.errorHandling.presentation.model

import androidx.compose.runtime.Stable
import dev.icerock.moko.resources.StringResource

@Stable
data class ValidationUiState(
    val isValid: Boolean,
    val messageRes: StringResource
)
