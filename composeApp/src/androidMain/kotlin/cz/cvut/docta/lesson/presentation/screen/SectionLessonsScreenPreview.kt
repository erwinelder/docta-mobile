package cz.cvut.docta.lesson.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonStatistics

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SectionLessonsScreenPreview() {
    val sectionName = "Section name"
    val activeDifficulty = LessonDifficulty.Easy
    val activeType = null
    val lessonList = listOf(
        Lesson.Default(
            id = 1,
            name = "Practice theory",
            statistics = LessonStatistics(isDone = true),
            difficulty = LessonDifficulty.Easy
        ),
        Lesson.Default(
            id = 2,
            name = "Practice limits vol.1",
            statistics = LessonStatistics(isDone = false),
            difficulty = LessonDifficulty.Medium
        ),
        Lesson.StepByStep(
            id = 3,
            name = "(x→2) lim x² = 4",
            statistics = LessonStatistics(isDone = true),
            difficulty = LessonDifficulty.Medium,
            description = "Solve the limit by definition"
        ),
        Lesson.Default(
            id = 4,
            name = "Practice limits vol.2",
            statistics = LessonStatistics(isDone = false),
            difficulty = LessonDifficulty.Medium
        ),
        Lesson.StepByStep(
            id = 5,
            name = "(x→∞) lim (3x³−2x²−1)",
            statistics = LessonStatistics(isDone = false),
            difficulty = LessonDifficulty.Medium,
            description = "Solve the limit"
        ),
        Lesson.Test(
            id = 6,
            name = "Review limits",
            statistics = LessonStatistics(isDone = false)
        ),
    )

    ScreenPreviewContainer {
        SectionLessonsScreen(
            sectionName = sectionName,
            onNavigateBack = {},
            activeDifficulty = activeDifficulty,
            onDifficultyChange = {},
            activeType = activeType,
            onTypeSelect = {},
            lessonList = lessonList
        )
    }
}