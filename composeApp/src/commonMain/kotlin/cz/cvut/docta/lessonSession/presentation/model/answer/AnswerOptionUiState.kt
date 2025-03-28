package cz.cvut.docta.lessonSession.presentation.model.answer

import androidx.compose.runtime.Stable

@Stable
data class AnswerOptionUiState(
    val id: Long,
    val text: String,
    val isSelected: Boolean
)