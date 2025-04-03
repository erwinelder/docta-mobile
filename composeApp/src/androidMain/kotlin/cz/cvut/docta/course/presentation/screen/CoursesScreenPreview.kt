package cz.cvut.docta.course.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.core.presentation.preview.ScreenWithBottomBarPreviewContainer
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.course.domain.model.CourseWithProgress

@Preview(device = Devices.PIXEL_7_PRO, locale = "cs")
@Composable
fun CoursesScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    userName: String = "Erwin",
    courses : List<CourseWithProgress> = listOf(
        CourseWithProgress.Completed(
            code = "code",
            locale = CourseLocale.English,
            name = "Návrh softwarových systémů",
            sectionCount = 14
        ),
        CourseWithProgress.InProgress(
            code = "code",
            locale = CourseLocale.English,
            name = "Základy vývoje pro Android",
            sectionCount = 14,
            completed = 10
        ),
        CourseWithProgress.NotStarted(
            code = "code",
            locale = CourseLocale.English,
            name = "Datové struktury a algoritmy",
            sectionCount = 14
        ),
    )
) {
    ScreenWithBottomBarPreviewContainer(appTheme = appTheme) {
        CoursesScreen(
            username = userName,
            onAddNewCourse = {},
            courses = courses,
//            courses = emptyList(),
            onCourseClick = {},
            requestState = null
//            requestState = RequestState.Loading(messageRes = SharedRes.strings.fetching_courses)
        )
    }
}