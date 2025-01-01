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
import cz.cvut.docta.course.presentation.screen.CoursesScreen
import cz.cvut.docta.course.presentation.viewModel.CoursesViewModel
import cz.cvut.docta.course_edit.presentation.screen.CourseEditingScreen
import cz.cvut.docta.lesson.domain.model.LessonState
import cz.cvut.docta.lesson.presentation.screen.LessonResultsScreen
import cz.cvut.docta.lesson.presentation.screen.LessonScreen
import cz.cvut.docta.lesson.presentation.screen.SectionLessonsScreen
import cz.cvut.docta.lesson.presentation.viewmodel.LessonQuestionsViewModel
import cz.cvut.docta.lesson.presentation.viewmodel.SectionLessonsViewModel
import cz.cvut.docta.section.presentation.screen.CourseSectionsScreen
import cz.cvut.docta.section.presentation.viewmodel.CourseSectionsViewModel
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

            val viewModel = koinViewModel<CourseSectionsViewModel>()
            LaunchedEffect(courseCode) {
                viewModel.fetchData(courseCode = courseCode)
            }

            val course by viewModel.course.collectAsStateWithLifecycle()
            val sectionList by viewModel.sectionList.collectAsStateWithLifecycle()

            CourseSectionsScreen(
                courseName = course?.name ?: "",
                onNavigateBack = navController::popBackStack,
                sectionList = sectionList,
                onSectionClick = { section ->
                    navController.navigate(MainScreens.SectionLessons(sectionId = section.id))
                }
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
                activeType = activeType,
                onTypeSelect = viewModel::setLessonFilterType,
                activeDifficulty = activeDifficulty,
                onDifficultyChange = viewModel::setLessonDifficulty,
                lessonList = lessonList
            )
        }
        composable<MainScreens.Lesson> { backStack ->
            val lessonId = backStack.toRoute<MainScreens.Lesson>().lessonId

            val viewModel = koinViewModel<LessonQuestionsViewModel>()
            LaunchedEffect(lessonId) {
                viewModel.fetchQuestions(lessonId = lessonId)
            }

            val progression by viewModel.progression.collectAsStateWithLifecycle()
            val lessonState by viewModel.lessonState.collectAsStateWithLifecycle()
            LaunchedEffect(lessonState) {
                if (lessonState !is LessonState.LessonQuestion) {
                    navController.navigate(MainScreens.LessonResults) {
                        popUpTo(MainScreens.Lesson) { inclusive = true }
                    }
                }
            }
            val currentQuestionWithAnswerInput = lessonState as? LessonState.LessonQuestion
            val questionCheckResult by viewModel.questionCheckResult.collectAsStateWithLifecycle()

            LessonScreen(
                progression = progression,
                questionWithAnswerInput = currentQuestionWithAnswerInput?.question,
                questionCheckResult = questionCheckResult,
                onContinueButtonClick = {
                    viewModel.resetAnswerCheckResult()
                    viewModel.applyNextQuestionOrShowResults()
                },
                onOpenAnswerInputChange = viewModel::changeOpenAnswerInput,
                onCheckOpenAnswer = viewModel::checkOpenAnswer,
                onBlanksAnswerInputChange = viewModel::changeBlankInput,
                onCheckBlanksAnswers = viewModel::checkBlanksAnswers,
                onAnswerOptionSelect = viewModel::selectOption,
                onCheckOptionAnswer = viewModel::checkOptionAnswer,
                onPairQuestionSelect = viewModel::selectPairQuestion,
                onPairAnswerSelect = viewModel::selectPairAnswer,
                onStepAnswerInputChange = viewModel::changeStepAnswerInput,
                onCheckStepAnswer = viewModel::checkStepAnswer
            )
        }
        composable<MainScreens.LessonResults> {
            val viewModel = koinViewModel<LessonQuestionsViewModel>()

            val lessonState by viewModel.lessonState.collectAsStateWithLifecycle()
            val lessonResults = lessonState as? LessonState.Results

            LessonResultsScreen(
                results = lessonResults?.lessonResults,
                onContinueButtonClick = {
                    navController.navigate(MainScreens.SectionLessons) {
                        popUpTo(MainScreens.SectionLessons) { inclusive = false}
                    }
                }
            )
        }

        composable("courseEditing/{courseCode}") { backStackEntry ->
            val courseCode = backStackEntry.arguments?.getString("courseCode") ?: return@composable
            CourseEditingScreen(
                courseCode = courseCode,
                onNavigateBack = navController::popBackStack
            )
        }
    }
}