package cz.cvut.docta.core.presentation

import androidx.compose.runtime.Composable
import cz.cvut.docta.course.presentation.screen.CoursesScreen

@Composable
fun MainApplicationContent() {
    CoursesScreen(
        courseList = emptyList()
    )
}