package cz.cvut.docta.course.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import cz.cvut.docta.course.presentation.screen.AddNewCourseScreen
import cz.cvut.docta.course.presentation.screen.CoursesScreen
import cz.cvut.docta.course.presentation.viewModel.AddNewCourseViewModel
import cz.cvut.docta.course.presentation.viewModel.CoursesViewModel
import cz.cvut.docta.lesson.presentation.navigation.LessonScreens
import cz.cvut.docta.lesson.presentation.screen.SectionLessonsScreen
import cz.cvut.docta.lesson.presentation.viewmodel.SectionLessonsViewModel
import cz.cvut.docta.section.presentation.screen.CourseSectionsScreen
import cz.cvut.docta.section.presentation.viewmodel.CourseSectionsViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.courseNavigationGraph(
    navController: NavHostController,
    navViewModel: NavViewModel
) {
    navigation<MainScreens.CoursesGraph>(
        startDestination = CourseScreens.Courses
    ) {
        composable<CourseScreens.Courses> {
            val courseContext = koinInject<CourseContext>()
            val viewModel = koinViewModel<CoursesViewModel>()

            val courses by viewModel.courses.collectAsStateWithLifecycle()

            LaunchedEffect(true) {
                courseContext.resetCourseCode()
            }

            CoursesScreen(
                onAddNewCourse = {
                    navViewModel.navigate(navController, CourseScreens.AddNewCourse)
                },
                onEditCourses = { /* TODO-COURSE */ },
                courses = courses,
                onCourseClick = { course ->
                    courseContext.setCourseCode(course.code)
                    navViewModel.navigate(
                        navController, CourseScreens.Sections(courseCode = course.code)
                    )
                }
            )
        }
        composable<CourseScreens.AddNewCourse> {
            val viewModel = koinViewModel<AddNewCourseViewModel>()

            val query by viewModel.query.collectAsStateWithLifecycle()
            val searchIsAllowed by viewModel.searchIsAllowed.collectAsStateWithLifecycle()
            val searchedCourseState by viewModel.courseSearchState.collectAsStateWithLifecycle()

            AddNewCourseScreen(
                onNavigateBack = navController::popBackStack,
                searchedCourseState = searchedCourseState,
                query = query,
                onQueryChange = viewModel::onQueryChange,
                searchIsAllowed = searchIsAllowed,
                onSearch = viewModel::searchForCourse,
                onSearchCancel = viewModel::cancelSearch,
                onTryAgain = viewModel::setCourseSearchPromptState,
                onAddCourse = {
                    // TODO-COURSE
                    navController.popBackStack()
                }
            )
        }
        composable<CourseScreens.Sections> { backStack ->
            val courseCode = backStack.toRoute<CourseScreens.Sections>().courseCode

            val viewModel = koinViewModel<CourseSectionsViewModel>()

            val course by viewModel.course.collectAsStateWithLifecycle()
            val sectionList by viewModel.sectionList.collectAsStateWithLifecycle()

            LaunchedEffect(courseCode) {
                viewModel.fetchData(courseCode = courseCode)
            }

            CourseSectionsScreen(
                courseName = course?.name ?: "",
                onNavigateBack = navController::popBackStack,
                sections = sectionList,
                onSectionClick = { section ->
                    navViewModel.navigate(
                        navController, CourseScreens.Lessons(sectionId = section.id)
                    )
                }
            )
        }
        composable<CourseScreens.Lessons> { backStack ->
            val sectionId = backStack.toRoute<CourseScreens.Lessons>().sectionId
            val courseContext = koinInject<CourseContext>()

            val viewModel = koinViewModel<SectionLessonsViewModel>()

            val section by viewModel.section.collectAsStateWithLifecycle()
            val activeDifficulty by viewModel.lessonDifficulty.collectAsStateWithLifecycle()
            val activeType by viewModel.lessonFilterType.collectAsStateWithLifecycle()
            val lessons by viewModel.sectionLessons.collectAsStateWithLifecycle()

            LaunchedEffect(sectionId) {
                courseContext.setSectionId(sectionId)
                viewModel.fetchData(sectionId = sectionId)
            }

            SectionLessonsScreen(
                sectionName = section?.name ?: "",
                onNavigateBack = navController::popBackStack,
                activeType = activeType,
                onTypeSelect = viewModel::setLessonFilterType,
                activeDifficulty = activeDifficulty,
                onDifficultyChange = viewModel::setLessonDifficulty,
                lessons = lessons,
                onLessonClick = { lesson ->
                    navViewModel.navigate(
                        navController, LessonScreens.LessonStarter(lessonId = lesson.id)
                    )
                }
            )
        }
    }
}