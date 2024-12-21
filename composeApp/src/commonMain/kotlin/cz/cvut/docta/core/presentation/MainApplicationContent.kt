package cz.cvut.docta.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.cvut.docta.course.presentation.screen.CoursesScreen
import cz.cvut.docta.course.presentation.viewModel.CoursesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainApplicationContent() {
    val coursesViewModel = koinViewModel<CoursesViewModel>()
    val courseList by coursesViewModel.courseList.collectAsStateWithLifecycle()

    CoursesScreen(
        courseList = courseList
    )
}