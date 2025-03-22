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
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerOptionUiState
import cz.cvut.docta.core.presentation.component.containers.GlassSurfaceWithAdaptiveColor
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors

@Composable
fun AnswerOptionsList(
    options: List<AnswerOptionUiState>,
    onOptionSelect: (Long) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = options) { option ->
            GlassSurfaceWithAdaptiveColor(
                gradientColorsPair = if (option.isSelected) DoctaColors.primaryGlassGradientPair else
                    DoctaColors.glassSurfaceGradientPair,
                cornerSize = 16.dp,
                modifier = Modifier.bounceClickEffect(.98f) { onOptionSelect(option.id) }
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
    }
}