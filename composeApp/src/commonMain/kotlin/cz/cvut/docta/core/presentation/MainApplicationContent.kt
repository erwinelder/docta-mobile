package cz.cvut.docta.core.presentation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import cz.cvut.docta.course.presentation.screen.CoursesScreen
import cz.cvut.docta.course.presentation.viewModel.CoursesViewModel
import cz.cvut.docta.course_editing.presentation.screen.CourseEditingScreen
import cz.cvut.docta.course_editing.presentation.viewmodel.CourseDraftViewModel
import cz.cvut.docta.lesson.presentation.component.LessonProgressBar
import cz.cvut.docta.lesson.presentation.navigation.LessonScreens
import cz.cvut.docta.lesson.presentation.navigation.lessonNavigationGraph
import cz.cvut.docta.lesson.presentation.screen.SectionLessonsScreen
import cz.cvut.docta.lesson.presentation.viewmodel.LessonProgressViewModel
import cz.cvut.docta.lesson.presentation.viewmodel.SectionLessonsViewModel
import cz.cvut.docta.section.presentation.screen.CourseSectionsScreen
import cz.cvut.docta.section.presentation.viewmodel.CourseSectionsViewModel
import cz.cvut.docta.section_editing.presentation.screen.SectionEditingScreen
import cz.cvut.docta.section_editing.presentation.viewmodel.SectionDraftViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainApplicationContent(
    navController: NavHostController = rememberNavController()
) {
    val navViewModel = koinViewModel<NavViewModel>()

    val lessonProgressViewModel = koinViewModel<LessonProgressViewModel>()
    val lessonProgression by lessonProgressViewModel.progression.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            LessonProgressBar(progression = lessonProgression)
        },
        backgroundColor = Color.Transparent
    ) { screenPadding ->
        NavHost(
            navController = navController,
            startDestination = MainScreens.Courses
        ) {
            composable<MainScreens.Courses> {
                val viewModel = koinViewModel<CoursesViewModel>()
                val courseContext = koinInject<CourseContext>()

                LaunchedEffect(true) {
                    courseContext.resetCourseCode()
                }

                val courseList by viewModel.courseList.collectAsStateWithLifecycle()

                CoursesScreen(
                    courses = courseList,
                    onCourseClick = { course ->
                        courseContext.setCourseCode(course.code)
                        navController.navigate(MainScreens.CourseSections(courseCode = course.code))
                    }
                )
            }
            composable<MainScreens.CourseSections> { backStack ->
                val courseCode = backStack.toRoute<MainScreens.CourseSections>().courseCode

                val viewModel = koinViewModel<CourseSectionsViewModel>()
                LaunchedEffect(courseCode) {
                    viewModel.fetchData(courseCode = courseCode)
                }

                val course by viewModel.course.collectAsStateWithLifecycle()
                val sectionList by viewModel.sectionList.collectAsStateWithLifecycle()

                CourseSectionsScreen(
                    courseName = course?.name ?: "",
                    onNavigateBack = navController::popBackStack,
                    sections = sectionList,
                    onSectionClick = { section ->
                        navController.navigate(MainScreens.SectionLessons(sectionId = section.id))
                    }
                )
            }
            composable<MainScreens.SectionLessons> { backStack ->
                val sectionId = backStack.toRoute<MainScreens.SectionLessons>().sectionId
                val courseContext = koinInject<CourseContext>()

                val viewModel = koinViewModel<SectionLessonsViewModel>()
                LaunchedEffect(sectionId) {
                    courseContext.setSectionId(sectionId)
                    viewModel.fetchData(sectionId = sectionId)
                }

                val section by viewModel.section.collectAsStateWithLifecycle()
                val activeDifficulty by viewModel.lessonDifficulty.collectAsStateWithLifecycle()
                val activeType by viewModel.lessonFilterType.collectAsStateWithLifecycle()
                val lessonList by viewModel.sectionLessons.collectAsStateWithLifecycle()

                SectionLessonsScreen(
                    sectionName = section?.name ?: "",
                    onNavigateBack = navController::popBackStack,
                    activeType = activeType,
                    onTypeSelect = viewModel::setLessonFilterType,
                    activeDifficulty = activeDifficulty,
                    onDifficultyChange = viewModel::setLessonDifficulty,
                    lessons = lessonList,
                    onLessonClick = { lesson ->
                        navController.navigate(LessonScreens.LessonStarter(lessonId = lesson.id))
                    }
                )
            }
            lessonNavigationGraph(
                navController = navController,
                navViewModel = navViewModel,
                screenPadding = screenPadding,
                lessonProgressViewModel = lessonProgressViewModel
            )
            composable<MainScreens.CourseEdit> { backStackEntry ->
                val courseCode = backStackEntry.toRoute<MainScreens.CourseEdit>().courseCode

                val viewModel = koinViewModel<CourseDraftViewModel>()

                val courseName by viewModel.courseName.collectAsStateWithLifecycle()
                val courseLocale by viewModel.courseLocale.collectAsStateWithLifecycle()

                LaunchedEffect(courseCode) {
                    viewModel.fetchCourseDraftData(courseCode)
                }

                CourseEditingScreen(
                    onNavigateBack = navController::popBackStack,
                    courseName = courseName,
                    onNameChange = viewModel::changeCourseName,
                    courseLocale = courseLocale,
                    onLocaleChange = viewModel::changeCourseLocale,
                    onSaveButtonClick = {
                        viewModel.saveCourseDraftToDatabase(courseCode = courseCode)
                    }
                )
            }
            composable<MainScreens.SectionEdit> { backStackEntry ->
                val sectionId = backStackEntry.toRoute<MainScreens.SectionEdit>().sectionId

                val viewModel = koinViewModel<SectionDraftViewModel>()

                val sectionName by viewModel.sectionName.collectAsStateWithLifecycle()

                LaunchedEffect(sectionId) {
                    viewModel.fetchSectionDraftData(sectionId)
                }

                SectionEditingScreen(
                    onNavigateBack = navController::popBackStack,
                    sectionName = sectionName,
                    onNameChange = viewModel::changeSectionName,
                    onSaveButtonClick = {
                        viewModel.saveSectionDraftToDatabase(sectionId = sectionId)
                    }
                )
            }
        }
    }
}