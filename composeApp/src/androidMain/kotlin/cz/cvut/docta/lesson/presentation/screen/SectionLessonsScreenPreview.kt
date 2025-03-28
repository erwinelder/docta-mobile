package cz.cvut.docta.lesson.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import cz.cvut.docta.lesson.domain.model.LessonWithProgress

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SectionLessonsScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    sectionName : String = "Section name",
    activeType: LessonFilterType? = null,
    lessons: List<LessonWithProgress> = listOf(
        LessonWithProgress.Default(
            id = 1,
            name = "Practice theory",
            description = "Practice the theory of limits",
            completed = true
        ),
        LessonWithProgress.Default(
            id = 2,
            name = "Practice limits vol.1",
            description = "Practice the limits",
            completed = false
        ),
        LessonWithProgress.StepByStep(
            id = 3,
            name = "(x→2) lim x² = 4",
            description = "Solve the limit by definition",
            completed = true
        ),
        LessonWithProgress.Default(
            id = 4,
            name = "Practice limits vol.2",
            description = "Practice the limits",
            completed = false
        ),
        LessonWithProgress.StepByStep(
            id = 5,
            name = "(x→∞) lim (3x³−2x²−1)",
            description = "Solve the limit",
            completed = false
        ),
        LessonWithProgress.Test(
            id = 6,
            name = "Review limits",
            description = "Test your knowledge of limits",
            completed = false
        ),
    )
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        SectionLessonsScreen(
            sectionName = sectionName,
            onNavigateBack = {},
            activeType = activeType,
            onTypeSelect = {},
            lessons = lessons,
            onLessonClick = {},
            requestState = null
        )
    }
}