package cz.cvut.docta.errorHandling.presentation.model

import androidx.compose.runtime.Stable
import dev.icerock.moko.resources.StringResource
import org.jetbrains.compose.resources.DrawableResource

@Stable
data class ResultState(
    val isSuccessful: Boolean,
    val titleRes: StringResource,
    val messageRes: StringResource,
    val buttonTextRes: StringResource,
    val buttonIconRes: DrawableResource?
)
