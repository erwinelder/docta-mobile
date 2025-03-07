package cz.cvut.docta.courseEditing.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import cz.cvut.docta.courseEditing.presentation.screen.CourseEditingScreen
import cz.cvut.docta.courseEditing.presentation.viewmodel.CourseDraftViewModel
import cz.cvut.docta.sectionEditing.presentation.screen.SectionEditingScreen
import cz.cvut.docta.sectionEditing.presentation.viewmodel.SectionDraftViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.courseManagementNavigationGraph(
    navController: NavHostController,
    navViewModel: NavViewModel
) {
    navigation<MainScreens.CourseManagementGraph>(
        startDestination = CourseManagementScreens.CourseEditing("")
    ) {
        composable<CourseManagementScreens.CourseEditing> { backStack ->
            val courseCode = backStack.toRoute<CourseManagementScreens.CourseEditing>().courseCode

            val viewModel = koinViewModel<CourseDraftViewModel>()

            val courseName by viewModel.courseName.collectAsStateWithLifecycle()
            val courseLocale by viewModel.courseLocale.collectAsStateWithLifecycle()
            val sections by viewModel.sectionList.collectAsStateWithLifecycle()

            LaunchedEffect(courseCode) {
                viewModel.fetchCourseDraftData(courseCode)
                viewModel.fetchCourseDraftSections(courseCode)
            }

            CourseEditingScreen(
                onNavigateBack = navController::popBackStack,
                courseName = courseName,
                onNameChange = viewModel::changeCourseName,
                courseLocale = courseLocale,
                onLocaleChange = viewModel::changeCourseLocale,
                onSaveButtonClick = {
                    viewModel.saveCourseDraftToDatabase(courseCode = courseCode)
                },
                sections = sections,
                onSectionClick = { sectionId ->
                    navViewModel.navigate(
                        navController, CourseManagementScreens.SectionEditing(sectionId))
                },
            )
        }
        composable<CourseManagementScreens.SectionEditing> { backStack ->
            val sectionId = backStack.toRoute<CourseManagementScreens.SectionEditing>().sectionId

            val viewModel = koinViewModel<SectionDraftViewModel>()

            val sectionName by viewModel.sectionName.collectAsStateWithLifecycle()

            val lessons by viewModel.lessonList.collectAsStateWithLifecycle()

            LaunchedEffect(sectionId) {
                viewModel.fetchSectionDraftData(sectionId)
            }

            SectionEditingScreen(
                onNavigateBack = navController::popBackStack,
                sectionName = sectionName,
                onNameChange = viewModel::changeSectionName,
                onSaveButtonClick = {

                    viewModel.saveSectionDraftToDatabase(sectionId = sectionId)
                },
                lessons = lessons,
                onLessonClick = { lessonId ->
                    navViewModel.navigate(
                        navController, CourseManagementScreens.LessonEditing(lessonId))
                },

            )
        }
    }
}