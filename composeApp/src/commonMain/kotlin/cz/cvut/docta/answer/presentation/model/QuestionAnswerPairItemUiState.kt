package cz.cvut.docta.answer.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cz.cvut.docta.core.presentation.theme.DoctaColors

data class QuestionAnswerPairItemUiState(
    val id: Long,
    val text: String,
    val isSelected: Boolean = false,
    val isCorrect: Boolean? = null,
    val isDisabled: Boolean = false
) {

    @Composable
    fun getColor(): Pair<Color, Color> {
        return when {
            isSelected -> DoctaColors.primaryGlassGradientPair
            isCorrect == true -> DoctaColors.successGlassGradientPair
            isCorrect == false -> DoctaColors.errorGlassGradientPair
            isDisabled -> Pair(Color.Transparent, Color.Transparent)
            else -> DoctaColors.glassSurfaceGradientPair
        }
    }

}
