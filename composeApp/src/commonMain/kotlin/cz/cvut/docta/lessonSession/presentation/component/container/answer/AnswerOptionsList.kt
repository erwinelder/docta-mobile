package cz.cvut.docta.lessonSession.presentation.component.container.answer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.component.container.GlassSurfaceWithAdaptiveColor
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerOptionUiState

@Composable
fun AnswerOptionsListComponent(
    options: List<AnswerOptionUiState>,
    onOptionSelect: (Long) -> Unit,
    checkState: AnswerCheckState<AnswerCheckResult.SingleOption>
) {
    val isIdle = checkState is AnswerCheckState.Idle
    val result = checkState.getResultOrNull()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = options) { option ->
            AnswerOptionComponent(
                option = option,
                onOptionSelect = onOptionSelect,
                isIdle = isIdle,
                result = result
            )
        }
    }
}

@Composable
private fun AnswerOptionComponent(
    option: AnswerOptionUiState,
    onOptionSelect: (Long) -> Unit,
    isIdle: Boolean,
    result: AnswerCheckResult.SingleOption?
) {
    val color = when {
        result == null && option.isSelected -> DoctaColors.primaryGlassGradientPair
        result != null && result.id == option.id -> DoctaColors.successGlassGradientPair
        result != null && !result.isCorrect && option.isSelected -> DoctaColors.errorGlassGradientPair
        else -> DoctaColors.glassSurfaceGradientPair
    }

    GlassSurfaceWithAdaptiveColor(
        gradientColorsPair = color,
        cornerSize = 16.dp,
        modifier = Modifier.bounceClickEffect(.98f, enabled = isIdle) {
            onOptionSelect(option.id)
        }
    ) {
        Text(
            text = option.text,
            fontSize = 18.sp,
            color = DoctaColors.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        )
    }
}