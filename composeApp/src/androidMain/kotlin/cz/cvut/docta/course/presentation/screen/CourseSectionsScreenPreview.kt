package cz.cvut.docta.course.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import cz.cvut.docta.section.domain.model.CourseSection
import cz.cvut.docta.section.domain.model.CourseSectionStatistics

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun CourseSectionsScreenPreview() {
    val courseSectionList = listOf(
        CourseSection(
            id = 1,
            name = "Section name 1",
            statistics = CourseSectionStatistics(
                correctAnswerAmount = 10, wrongAnswerAmount = 20
            )
        ),
        CourseSection(
            id = 2,
            name = "Section name 2",
            statistics = CourseSectionStatistics(
                correctAnswerAmount = 30, wrongAnswerAmount = 40
            )
        ),
    )

    ScreenPreviewContainer {
        CourseSectionsScreen(
            onBackButtonClick = {},
            courseSectionList = courseSectionList,
            onSectionClick = {}
        )
    }
}