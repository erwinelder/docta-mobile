package cz.cvut.docta.lessonSession.presentation.model.question

import androidx.compose.runtime.Stable

@Stable
data class CategorizationOptionUiState(
    val id: Long,
    val text: String,
    val selectedCategoryId: Long?
)