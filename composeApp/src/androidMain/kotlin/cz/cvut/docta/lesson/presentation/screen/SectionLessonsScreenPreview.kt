package cz.cvut.docta.lesson.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonFilterType

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SectionLessonsScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    sectionName : String = "Section name",
    activeDifficulty: LessonDifficulty = LessonDifficulty.Easy,
    activeType: LessonFilterType? = null,
    lessons: List<LessonWithProgress> = listOf(
        LessonWithProgress.Default(
            id = 1,
            name = "Practice theory",
            completed = true,
            difficulty = LessonDifficulty.Easy,
            matchAllTags = false
        ),
        LessonWithProgress.Default(
            id = 2,
            name = "Practice limits vol.1",
            completed = false,
            difficulty = LessonDifficulty.Medium,
            matchAllTags = false
        ),
        LessonWithProgress.StepByStep(
            id = 3,
            name = "(x→2) lim x² = 4",
            completed = true,
            difficulty = LessonDifficulty.Medium,
            description = "Solve the limit by definition"
        ),
        LessonWithProgress.Default(
            id = 4,
            name = "Practice limits vol.2",
            completed = false,
            difficulty = LessonDifficulty.Medium,
            matchAllTags = false
        ),
        LessonWithProgress.StepByStep(
            id = 5,
            name = "(x→∞) lim (3x³−2x²−1)",
            completed = false,
            difficulty = LessonDifficulty.Medium,
            description = "Solve the limit"
        ),
        LessonWithProgress.Test(
            id = 6,
            name = "Review limits",
            completed = false,
            matchAllTags = false
        ),
    )
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        SectionLessonsScreen(
            sectionName = sectionName,
            onNavigateBack = {},
            activeDifficulty = activeDifficulty,
            onDifficultyChange = {},
            activeType = activeType,
            onTypeSelect = {},
            lessons = lessons,
            onLessonClick = {}
        )
    }
}