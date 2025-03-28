package cz.cvut.docta.section.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.section.domain.model.SectionWithProgress

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun CourseSectionsScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    courseName: String = "Course name",
    sections: List<SectionWithProgress> = listOf(
        SectionWithProgress.Completed(
            courseCode = "code",
            id = 1,
            orderNum = 1,
            name = "Completed section name",
            lessonCount = 12
        ),
        SectionWithProgress.InProgress(
            courseCode = "code",
            id = 2,
            orderNum = 2,
            name = "In progress section name",
            lessonCount = 10,
            completed = 3
        ),
        SectionWithProgress.NotStarted(
            courseCode = "code",
            id = 3,
            orderNum = 3,
            name = "Not started section name",
            lessonCount = 8
        )
    )
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        CourseSectionsScreen(
            courseName = courseName,
            onNavigateBack = {},
            sections = sections,
            onSectionClick = {},
            requestState = null
        )
    }
}