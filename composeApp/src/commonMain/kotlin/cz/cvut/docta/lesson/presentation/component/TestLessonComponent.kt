package cz.cvut.docta.lesson.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lesson.domain.model.LessonWithProgress

@Composable
fun TestLessonComponent(
    lesson: LessonWithProgress.Test,
    onClick: (LessonWithProgress) -> Unit
) {
    LessonWithProgressContainer(lesson = lesson, height = 70.dp, onClick = onClick) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = lesson.name,
                color = DoctaColors.onSurface,
                fontSize = 18.sp,
                fontFamily = Manrope,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = lesson.description,
                color = DoctaColors.outline,
                fontSize = 15.sp,
                fontFamily = Manrope,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}