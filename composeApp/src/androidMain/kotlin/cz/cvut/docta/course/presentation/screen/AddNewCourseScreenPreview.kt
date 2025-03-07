package cz.cvut.docta.course.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.course.domain.model.CourseSearchState

@Preview(device = PIXEL_7_PRO)
@Composable
fun AddNewCourseScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    query: String = "",
    searchedCourseState: CourseSearchState = CourseSearchState.SearchedCourse(
        course = Course(
            code = "nss",
            locale = CourseLocale.English,
            name = "Návrh softwarových systémů",
        )
    )
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        AddNewCourseScreen(
            onNavigateBack = {},
            searchedCourseState = CourseSearchState.SearchedCourse(
                course = null
            ),
            query = query,
            onQueryChange = {},
            searchIsAllowed = true,
            onSearch = {},
            onSearchCancel = {},
            onTryAgain = {},
            onAddCourse = {}
        )
    }
}