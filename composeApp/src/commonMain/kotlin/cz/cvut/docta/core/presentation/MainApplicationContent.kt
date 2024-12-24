package cz.cvut.docta.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.section.presentation.screen.CourseSectionsScreen
import cz.cvut.docta.course.presentation.screen.CoursesScreen
import cz.cvut.docta.course.presentation.viewModel.CoursesViewModel
import cz.cvut.docta.lesson.presentation.screen.SectionLessonsScreen
import cz.cvut.docta.lesson.presentation.viewmodel.SectionLessonsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainApplicationContent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreens.Courses
    ) {
        composable<MainScreens.Courses> {
            val viewModel = koinViewModel<CoursesViewModel>()

            val courseList by viewModel.courseList.collectAsStateWithLifecycle()

            CoursesScreen(
                courseList = courseList,
                onCourseClick = { course ->
                    navController.navigate(MainScreens.CourseSections(courseCode = course.code))
                }
            )
        }
        composable<MainScreens.CourseSections> { backStack ->
            val courseCode = backStack.toRoute<MainScreens.CourseSections>().courseCode

            // TODO-COURSE-SECTIONS: collect course sections from relative view model

            CourseSectionsScreen(
                onBackButtonClick = navController::popBackStack,
                courseSectionList = emptyList(), // TODO-COURSE-SECTIONS: pass course sections,
                onSectionClick = { /* TODO: navigation to the specific section screen */ }
            )
        }
        composable<MainScreens.SectionLessons> { backStack ->
            val sectionId = backStack.toRoute<MainScreens.SectionLessons>().sectionId

            val viewModel = koinViewModel<SectionLessonsViewModel>()
            LaunchedEffect(sectionId) {
                viewModel.fetchData(sectionId = sectionId)
            }

            val section by viewModel.section.collectAsStateWithLifecycle()
            val activeDifficulty by viewModel.lessonDifficulty.collectAsStateWithLifecycle()
            val activeType by viewModel.lessonFilterType.collectAsStateWithLifecycle()
            val lessonList by viewModel.sectionLessons.collectAsStateWithLifecycle()

            SectionLessonsScreen(
                sectionName = section?.name ?: "",
                onNavigateBack = navController::popBackStack,
                activeDifficulty = activeDifficulty,
                onDifficultyChange = viewModel::setLessonDifficulty,
                activeType = activeType,
                onTypeSelect = viewModel::setLessonFilterType,
                lessonList = lessonList
            )
        }
    }
}