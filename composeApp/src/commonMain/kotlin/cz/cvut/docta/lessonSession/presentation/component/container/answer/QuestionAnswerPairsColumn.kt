package cz.cvut.docta.lessonSession.presentation.component.container.answer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.lessonSession.presentation.model.answer.QuestionAnswerPairItemUiState
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.containers.GlassSurfaceWithAdaptiveColor
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors

@Composable
fun RowScope.QuestionAnswerPairsColumn(
    items: List<QuestionAnswerPairItemUiState>,
    onItemSelect: (Long) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.weight(1f)
    ) {
        items.forEach { question ->
            QuestionAnswerPairComponent(state = question, onClick = onItemSelect)
        }
    }
}

@Composable
private fun QuestionAnswerPairComponent(
    state: QuestionAnswerPairItemUiState,
    onClick: (Long) -> Unit
) {
    GlassSurfaceWithAdaptiveColor(
        gradientColorsPair = state.getColor(),
        modifier = Modifier.bounceClickEffect(enabled = !state.isDisabled) { onClick(state.id) },
        filledWidths = FilledWidthByScreenType(1f, 1f, 1f),
        cornerSize = 16.dp
    ) {
        Text(
            text = state.text,
            fontSize = 17.sp,
            color = if (state.isDisabled) DoctaColors.outline else DoctaColors.onSurface,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )
    }
}