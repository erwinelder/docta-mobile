package cz.cvut.docta.lesson.presentation.component

import androidx.compose.runtime.Composable
import cz.cvut.docta.lesson.domain.model.LessonWithProgress

@Composable
fun LessonWithProgressComponent(
    lesson: LessonWithProgress,
    onClick: (LessonWithProgress) -> Unit,
) {
    when (lesson) {
        is LessonWithProgress.Default -> {
            DefaultLessonComponent(lesson = lesson, onClick = onClick)
        }
        is LessonWithProgress.StepByStep -> {
            StepByStepLessonComponent(lesson = lesson, onClick = onClick)
        }
        is LessonWithProgress.Test -> {
            TestLessonComponent(lesson = lesson, onClick = onClick)
        }
    }
}