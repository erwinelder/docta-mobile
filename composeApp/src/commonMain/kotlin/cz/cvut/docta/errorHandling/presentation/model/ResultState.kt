package cz.cvut.docta.errorHandling.presentation.model

import androidx.compose.runtime.Stable
import dev.icerock.moko.resources.StringResource

@Stable
data class ResultState(
    val isSuccessful: Boolean,
    val titleRes: StringResource,
    val messageRes: StringResource
)
