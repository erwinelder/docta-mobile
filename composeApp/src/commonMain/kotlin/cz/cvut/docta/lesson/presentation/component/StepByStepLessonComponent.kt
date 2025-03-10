package cz.cvut.docta.lesson.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lesson.domain.model.Lesson

@Composable
fun StepByStepLessonComponent(
    state: Lesson.StepByStep,
    onClick: (Lesson) -> Unit
) {
    LessonComponent(state = state, onClick = onClick, buttonVerticalPadding = 32.dp) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 4.dp)
        ) {
            Text(
                text = state.description,
                color = DoctaColors.outline,
                fontSize = 16.sp,
                fontFamily = Manrope,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = state.name,
                color = DoctaColors.onSurface,
                fontSize = 18.sp,
                fontFamily = Manrope,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            LessonDifficultyFlagComponent(state.difficulty)
        }
    }
}