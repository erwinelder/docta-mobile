package cz.cvut.docta.section.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.section.domain.model.SectionWithStatistics
import cz.cvut.docta.section.domain.model.SectionStatistics

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun CourseSectionsScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    courseName: String = "Course name",
    sections: List<SectionWithStatistics> = listOf(
        SectionWithStatistics(
            id = 1,
            name = "Section name 1",
            statistics = SectionStatistics(
                correctAnswerAmount = 10, wrongAnswerAmount = 20
            )
        ),
        SectionWithStatistics(
            id = 2,
            name = "Section name 2",
            statistics = SectionStatistics(
                correctAnswerAmount = 30, wrongAnswerAmount = 40
            )
        ),
        SectionWithStatistics(
            id = 3,
            name = "Section name 3",
            statistics = SectionStatistics(
                correctAnswerAmount = 30, wrongAnswerAmount = 40
            )
        ),
        SectionWithStatistics(
            id = 4,
            name = "Section name 4",
            statistics = SectionStatistics(
                correctAnswerAmount = 30, wrongAnswerAmount = 40
            )
        ),
        SectionWithStatistics(
            id = 5,
            name = "Section name 5",
            statistics = SectionStatistics(
                correctAnswerAmount = 30, wrongAnswerAmount = 40
            )
        ),
        SectionWithStatistics(
            id = 6,
            name = "Section name 6",
            statistics = SectionStatistics(
                correctAnswerAmount = 30, wrongAnswerAmount = 40
            )
        ),
        SectionWithStatistics(
            id = 7,
            name = "Section name 7",
            statistics = SectionStatistics(
                correctAnswerAmount = 30, wrongAnswerAmount = 40
            )
        ),
        SectionWithStatistics(
            id = 8,
            name = "Section name 8",
            statistics = SectionStatistics(
                correctAnswerAmount = 30, wrongAnswerAmount = 40
            )
        ),
        SectionWithStatistics(
            id = 9,
            name = "Section name 9",
            statistics = SectionStatistics(
                correctAnswerAmount = 30, wrongAnswerAmount = 40
            )
        ),
        SectionWithStatistics(
            id = 10,
            name = "Section name 10",
            statistics = SectionStatistics(
                correctAnswerAmount = 30, wrongAnswerAmount = 40
            )
        ),
    )
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        CourseSectionsScreen(
            courseName = courseName,
            onNavigateBack = {},
            sections = sections,
            onSectionClick = {}
        )
    }
}