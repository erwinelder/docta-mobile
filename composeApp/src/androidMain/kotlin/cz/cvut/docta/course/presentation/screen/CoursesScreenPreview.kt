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
        CourseLightweight(code = "maa", locale = "cs", name = "MAA")
    )

    ScreenPreviewContainer {
        CoursesScreen(
            courseList = courseList
        )
    }
}