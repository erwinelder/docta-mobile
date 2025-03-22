package cz.cvut.docta.course.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.course.domain.model.CourseWithProgress

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun CoursesScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    courses : List<CourseWithProgress> = listOf(
        CourseWithProgress.Completed(
            code = "code",
            locale = CourseLocale.English,
            name = "Completed course name",
            sectionCount = 14
        ),
        CourseWithProgress.InProgress(
            code = "code",
            locale = CourseLocale.English,
            name = "In progress course name",
            sectionCount = 14,
            completed = 10
        ),
        CourseWithProgress.NotStarted(
            code = "code",
            locale = CourseLocale.English,
            name = "Not started course name",
            sectionCount = 14
        ),
    )
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        CoursesScreen(
            username = "username",
            onAddNewCourse = {},
            onEditCourses = {},
            courses = courses,
            onCourseClick = {}
        )
    }
}