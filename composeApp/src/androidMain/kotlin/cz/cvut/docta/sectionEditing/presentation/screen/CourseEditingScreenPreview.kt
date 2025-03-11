package cz.cvut.docta.sectionEditing.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import cz.cvut.docta.courseEditing.presentation.screen.CourseEditingScreen
import cz.cvut.docta.sectionEditing.domain.model.SectionDraft

@Preview(device = PIXEL_7_PRO)
@Composable
fun CourseEditingScreenPreview(
    appTheme: AppTheme = AppTheme.Light
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        CourseEditingScreen(
            onNavigateBack = {},
            courseName = "Course name",
            onNameChange = {},
            courseLocale = "English",
            onLocaleChange = {},
            onSaveButtonClick = {},
            sections = listOf(
                SectionDraft(
                    courseCode = "1",
                    id = 4484,
                    name = "Section 1"
                ),
                SectionDraft(
                    courseCode = "1",
                    id = 4491,
                    name = "Section 12"
                ),
                SectionDraft(
                    courseCode = "1",
                    id = 4503,
                    name = "Section 23"
                )
            ),
            onSectionClick = {}
        )
    }
}