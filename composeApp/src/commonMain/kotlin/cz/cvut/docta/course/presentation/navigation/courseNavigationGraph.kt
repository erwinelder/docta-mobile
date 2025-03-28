package cz.cvut.docta.course.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.navigation.sharedKoinNavViewModel
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import cz.cvut.docta.course.presentation.screen.AddNewCourseScreen
import cz.cvut.docta.course.presentation.screen.CoursesScreen
import cz.cvut.docta.course.presentation.viewModel.AddNewCourseViewModel
import cz.cvut.docta.course.presentation.viewModel.CoursesViewModel
import cz.cvut.docta.lesson.presentation.navigation.LessonSessionScreens
import cz.cvut.docta.lesson.presentation.screen.SectionLessonsScreen
import cz.cvut.docta.lesson.presentation.viewmodel.SectionLessonsViewModel
import cz.cvut.docta.section.presentation.screen.CourseSectionsScreen
import cz.cvut.docta.section.presentation.viewmodel.CourseSectionsViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.courseNavigationGraph(
    navController: NavHostController,
    navViewModel: NavViewModel,
    screenPadding: PaddingValues
) {
    navigation<MainScreens.Courses>(
        startDestination = CourseScreens.Courses
    ) {
        composable<CourseScreens.Courses> { backStack ->
            val userContext = koinInject<UserContext>()
            val courseContext = koinInject<CourseContext>()

            val viewModel = backStack.sharedKoinNavViewModel<CoursesViewModel>(navController)

            val courses by viewModel.courses.collectAsStateWithLifecycle()
            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            LaunchedEffect(true) {
                courseContext.resetCourseCode()
                courseContext.resetSectionId()
            }

            CoursesScreen(
                screenPadding = screenPadding,
                username = userContext.name,
                onAddNewCourse = {
                    navViewModel.navigate(
                        navController = navController, screen = CourseScreens.AddNewCourse
                    )
                },
                onEditCourses = { /* TODO-COURSE */ },
                courses = courses,
                onCourseClick = { course ->
                    courseContext.setCourseCode(code = course.code)
                    navViewModel.navigate(
                        navController = navController,
                        screen = CourseScreens.Sections(courseCode = course.code)
                    )
                },
                requestState = requestState
            )
        }
        composable<CourseScreens.AddNewCourse> { backStack ->
            val addCourseViewModel = koinViewModel<AddNewCourseViewModel>()
            val coursesViewModel = backStack.sharedKoinNavViewModel<CoursesViewModel>(navController)

            val query by addCourseViewModel.query.collectAsStateWithLifecycle()
            val searchIsAllowed by addCourseViewModel.searchIsAllowed.collectAsStateWithLifecycle()
            val searchedCourseState by addCourseViewModel.courseSearchState.collectAsStateWithLifecycle()

            AddNewCourseScreen(
                screenPadding = screenPadding,
                onNavigateBack = navController::popBackStack,
                searchedCourseState = searchedCourseState,
                query = query,
                onQueryChange = addCourseViewModel::onQueryChange,
                searchIsAllowed = searchIsAllowed,
                onSearch = addCourseViewModel::searchForCourse,
                onSearchCancel = addCourseViewModel::cancelSearch,
                onTryAgain = addCourseViewModel::setCourseSearchPromptState,
                onAddCourse = { course ->
                    coursesViewModel.addCourse(course = course)
                    addCourseViewModel.addCourseToChosenCourses(course = course)
                    navController.popBackStack()
                }
            )
        }
        composable<CourseScreens.Sections> { backStack ->
            val courseCode = backStack.toRoute<CourseScreens.Sections>().courseCode
            val courseContext = koinInject<CourseContext>()

            val viewModel = koinViewModel<CourseSectionsViewModel> {
                parametersOf(courseCode)
            }

            val course by viewModel.course.collectAsStateWithLifecycle()
            val sections by viewModel.sections.collectAsStateWithLifecycle()
            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            LaunchedEffect(true) {
                courseContext.resetSectionId()
            }

            CourseSectionsScreen(
                screenPadding = screenPadding,
                courseName = course?.name ?: "",
                onNavigateBack = navController::popBackStack,
                sections = sections,
                onSectionClick = { section ->
                    courseContext.setSectionId(sectionId = section.id)
                    navViewModel.navigate(
                        navController = navController,
                        screen = CourseScreens.Lessons(sectionId = section.id)
                    )
                },
                requestState = requestState
            )
        }
        composable<CourseScreens.Lessons> { backStack ->
            val sectionId = backStack.toRoute<CourseScreens.Lessons>().sectionId
            val courseContext = koinInject<CourseContext>()

            val viewModel = koinViewModel<SectionLessonsViewModel> {
                parametersOf(sectionId)
            }

            val section by viewModel.section.collectAsStateWithLifecycle()
            val activeType by viewModel.lessonFilterType.collectAsStateWithLifecycle()
            val lessons by viewModel.sectionLessons.collectAsStateWithLifecycle()
            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            SectionLessonsScreen(
                screenPadding = screenPadding,
                sectionName = section?.name ?: "",
                onNavigateBack = navController::popBackStack,
                activeType = activeType,
                onTypeSelect = viewModel::setLessonFilterType,
                lessons = lessons,
                onLessonClick = { lesson ->
                    courseContext.setLesson(lesson = lesson)
                    navViewModel.navigate(
                        navController = navController,
                        screen = LessonSessionScreens.LessonStarter
                    )
                },
                requestState = requestState
            )
        }
    }
}