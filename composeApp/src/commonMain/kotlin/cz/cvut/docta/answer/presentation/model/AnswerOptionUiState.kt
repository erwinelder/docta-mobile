package cz.cvut.docta.answer.presentation.model

import androidx.compose.runtime.Stable

@Stable
data class AnswerOptionUiState(
    val id: Long,
    val text: String,
    val isSelected: Boolean
)
