package cz.cvut.docta.sectionEditing.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import cz.cvut.docta.courseEditing.presentation.screen.CourseEditingScreen
import cz.cvut.docta.section.domain.model.SectionStatistics
import cz.cvut.docta.section.domain.model.SectionWithStatistics

@Preview(device = PIXEL_7_PRO)
@Composable
fun CourseEditingScreenPreview() {
    ScreenPreviewContainer(appTheme = AppTheme.Light) {
        CourseEditingScreen(
            onNavigateBack = {},
            courseName = "Course name",
            onNameChange = {},
            courseLocale = "English",
            onLocaleChange = {},
            onSaveButtonClick = {},
            sections = listOf(
                SectionWithStatistics(
                    id = 4484,
                    name = "Section 1",
                    statistics = SectionStatistics()
                )
            ),
            onSectionClick = {}
        )
    }
}