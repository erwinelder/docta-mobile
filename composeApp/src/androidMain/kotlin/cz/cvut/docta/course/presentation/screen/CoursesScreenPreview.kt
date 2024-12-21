package cz.cvut.docta.course.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import cz.cvut.docta.course.domain.model.CourseLightweight

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun CoursesScreenPreview() {
    val courseList = listOf(
        CourseLightweight(
            code = "course_code_1",
            locale = "cs",
            name = "Course name 1"
        ),
        CourseLightweight(
            code = "course_code_2",
            locale = "en",
            name = "Course name 2"
        ),
    )

    ScreenPreviewContainer {
        CoursesScreen(
            courseList = courseList
        )
    }
}