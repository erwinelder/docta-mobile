package cz.cvut.docta.lessonSession.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.navigation.sharedKoinNavViewModel
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import cz.cvut.docta.course.presentation.navigation.CourseScreens
import cz.cvut.docta.lessonSession.presentation.screen.lesson.LessonResultsScreen
import cz.cvut.docta.lessonSession.presentation.screen.lesson.LessonStarterScreen
import cz.cvut.docta.lesson.presentation.utils.getLessonScreenToNavigateTo
import cz.cvut.docta.lessonSession.presentation.viewmodel.lesson.LessonProgressViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.lesson.LessonResultsViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.lesson.LessonViewModel
import cz.cvut.docta.lessonSession.mapper.getSessionOptions
import cz.cvut.docta.lessonSession.presentation.model.QuestionWrapper
import cz.cvut.docta.lessonSession.presentation.screen.question.AnswerOptionsQuestionScreen
import cz.cvut.docta.lessonSession.presentation.screen.question.CategorizationQuestionScreen
import cz.cvut.docta.lessonSession.presentation.screen.question.FillInBlanksQuestionScreen
import cz.cvut.docta.lessonSession.presentation.screen.question.OpenAnswerQuestionScreen
import cz.cvut.docta.lessonSession.presentation.screen.question.QuestionAnswerPairsQuestionScreen
import cz.cvut.docta.lessonSession.presentation.viewmodel.question.AnswerOptionsQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.question.CategorizationQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.question.FillInBlanksQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.question.OpenAnswerQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.question.QuestionAnswerPairsQuestionViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.lessonSessionNavigationGraph(
    navController: NavHostController,
    navViewModel: NavViewModel,
    screenPadding: PaddingValues,
    lessonProgressViewModel: LessonProgressViewModel
) {
    navigation<MainScreens.LessonSession>(
        startDestination = LessonSessionScreens.LessonStarter
    ) {
        composable<LessonSessionScreens.LessonStarter> { backStack ->
            val courseContext = koinInject<CourseContext>()
            val sessionOptions = courseContext.getLesson()?.getSessionOptions() ?: run {
                navViewModel.popBackStack(
                    navController = navController,
                    screen = CourseScreens.Lessons(sectionId = courseContext.getSectionId())
                )
                return@composable
            }

            val viewModel = backStack.sharedKoinNavViewModel<LessonViewModel>(navController)

            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            LaunchedEffect(sessionOptions) {
                val questionCount = viewModel.fetchQuestions(sessionOptions = sessionOptions)
                lessonProgressViewModel.setProgression(questionCount = questionCount)

                viewModel.getNextQuestionOrNull()?.getLessonScreenToNavigateTo()?.let {
                    navViewModel.navigate(navController = navController, screen = it)
                    viewModel.resetRequestState()
                }
            }

            LessonStarterScreen(
                screenPadding = screenPadding,
                requestState = requestState,
                onNavigateBack = {
                    navViewModel.popBackStack(
                        navController = navController,
                        screen = CourseScreens.Lessons(sectionId = courseContext.getSectionId())
                    )
                }
            )
        }
        composable<LessonSessionScreens.OpenAnswerQuestion> { backStack ->
            val lessonViewModel = backStack.sharedKoinNavViewModel<LessonViewModel>(navController)
            val questionViewModel = koinViewModel<OpenAnswerQuestionViewModel> {
                parametersOf(
                    lessonViewModel.getNextQuestionAs<QuestionWrapper.OpenAnswer>()
                )
            }

            val answerInput by questionViewModel.openAnswer.collectAsStateWithLifecycle()
            val checkIsAllowed by questionViewModel.checkIsAllowed.collectAsStateWithLifecycle()
            val checkRequestState by questionViewModel.checkRequestState.collectAsStateWithLifecycle()

            OpenAnswerQuestionScreen(
                screenPadding = screenPadding,
                questionMaterials = questionViewModel.materials,
                questionText = questionViewModel.questionText,
                answerText = answerInput,
                onAnswerChange = questionViewModel::onOpenAnswerChange,
                checkIsAllowed = checkIsAllowed,
                checkRequestState = checkRequestState,
                onCheckButtonClick = questionViewModel::checkAnswer,
                onContinueButtonClick = {
                    questionViewModel.getQuestionWithCheckResult()
                        ?.let(lessonViewModel::processQuestionCheckResult)
                        ?.let {
                            if (it) lessonProgressViewModel.incrementProgression()
                        }
                    navViewModel.navigateToNextQuestionOrResultsScreen(
                        navController = navController,
                        nextQuestionScreen = lessonViewModel.processToNextQuestion(),
                        onResetLessonProgression = lessonProgressViewModel::resetProgression
                    )
                }
            )
        }
        composable<LessonSessionScreens.FillInBlanksQuestion> { backStack ->
            val lessonViewModel = backStack.sharedKoinNavViewModel<LessonViewModel>(navController)
            val questionViewModel = koinViewModel<FillInBlanksQuestionViewModel> {
                parametersOf(
                    lessonViewModel.getNextQuestionAs<QuestionWrapper.FillInBlanks>()
                )
            }

            val blanksAnswers by questionViewModel.blanksAnswers.collectAsStateWithLifecycle()
            val checkIsAllowed by questionViewModel.checkIsAllowed.collectAsStateWithLifecycle()
            val checkRequestState by questionViewModel.checkRequestState.collectAsStateWithLifecycle()

            FillInBlanksQuestionScreen(
                screenPadding = screenPadding,
                questionMaterials = questionViewModel.materials,
                questionUnits = questionViewModel.questionUnits,
                blanksAnswers = blanksAnswers,
                onBlankAnswerChange = questionViewModel::onBlankAnswerChange,
                checkIsAllowed = checkIsAllowed,
                checkRequestState = checkRequestState,
                onCheckButtonClick = questionViewModel::checkAnswer,
                onContinueButtonClick = {
                    questionViewModel.getQuestionWithCheckResult()
                        ?.let(lessonViewModel::processQuestionCheckResult)
                        ?.let {
                            if (it) lessonProgressViewModel.incrementProgression()
                        }
                    navViewModel.navigateToNextQuestionOrResultsScreen(
                        navController = navController,
                        nextQuestionScreen = lessonViewModel.processToNextQuestion(),
                        onResetLessonProgression = lessonProgressViewModel::resetProgression
                    )
                }
            )
        }
        composable<LessonSessionScreens.AnswerOptionsQuestion> { backStack ->
            val lessonViewModel = backStack.sharedKoinNavViewModel<LessonViewModel>(navController)
            val questionViewModel = koinViewModel<AnswerOptionsQuestionViewModel> {
                parametersOf(
                    lessonViewModel.getNextQuestionAs<QuestionWrapper.AnswerOptions>()
                )
            }

            val options by questionViewModel.options.collectAsStateWithLifecycle()
            val checkIsAllowed by questionViewModel.checkIsAllowed.collectAsStateWithLifecycle()
            val checkRequestState by questionViewModel.checkRequestState.collectAsStateWithLifecycle()

            AnswerOptionsQuestionScreen(
                screenPadding = screenPadding,
                questionMaterials = questionViewModel.materials,
                questionText = questionViewModel.questionText,
                options = options,
                onOptionSelect = questionViewModel::onOptionSelect,
                checkIsAllowed = checkIsAllowed,
                checkRequestState = checkRequestState,
                onCheckButtonClick = questionViewModel::checkAnswer,
                onContinueButtonClick = {
                    questionViewModel.getQuestionWithCheckResult()
                        ?.let(lessonViewModel::processQuestionCheckResult)
                        ?.let {
                            if (it) lessonProgressViewModel.incrementProgression()
                        }
                    navViewModel.navigateToNextQuestionOrResultsScreen(
                        navController = navController,
                        nextQuestionScreen = lessonViewModel.processToNextQuestion(),
                        onResetLessonProgression = lessonProgressViewModel::resetProgression
                    )
                }
            )
        }
        composable<LessonSessionScreens.CategorizationQuestion> { backStack ->
            val lessonViewModel = backStack.sharedKoinNavViewModel<LessonViewModel>(navController)

            val questionViewModel = koinViewModel<CategorizationQuestionViewModel> {
                parametersOf(
                    lessonViewModel.getNextQuestionAs<QuestionWrapper.Categorization>()
                )
            }

            val optionsWithCategories by questionViewModel.optionsWithCategories.collectAsStateWithLifecycle()
            val checkIsAllowed by questionViewModel.checkIsAllowed.collectAsStateWithLifecycle()
            val checkRequestState by questionViewModel.checkRequestState.collectAsStateWithLifecycle()

            CategorizationQuestionScreen(
                screenPadding = screenPadding,
                questionText = questionViewModel.questionText,
                questionMaterials = questionViewModel.materials,
                categories = questionViewModel.categories,
                optionsWithCategories = optionsWithCategories,
                onOptionCategorySelect = questionViewModel::selectOptionCategory,
                checkIsAllowed = checkIsAllowed,
                checkRequestState = checkRequestState,
                onCheckButtonClick = questionViewModel::checkAnswer,
                onContinueButtonClick = {
                    questionViewModel.getQuestionWithCheckResult()
                        ?.let(lessonViewModel::processQuestionCheckResult)
                        ?.let {
                            if (it) lessonProgressViewModel.incrementProgression()
                        }
                    navViewModel.navigateToNextQuestionOrResultsScreen(
                        navController = navController,
                        nextQuestionScreen = lessonViewModel.processToNextQuestion(),
                        onResetLessonProgression = lessonProgressViewModel::resetProgression
                    )
                }
            )
        }
        composable<LessonSessionScreens.QuestionAnswerPairsQuestion> { backStack ->
            val lessonViewModel = backStack.sharedKoinNavViewModel<LessonViewModel>(navController)
            val questionViewModel = koinViewModel<QuestionAnswerPairsQuestionViewModel> {
                parametersOf(
                    lessonViewModel.getNextQuestionAs<QuestionWrapper.QuestionAnswerPairs>()
                )
            }

            val questions by questionViewModel.questions.collectAsStateWithLifecycle()
            val answers by questionViewModel.answers.collectAsStateWithLifecycle()
            val checkRequestState by questionViewModel.checkRequestState.collectAsStateWithLifecycle()
            val continueButtonEnabled by questionViewModel.continueButtonEnabled.collectAsStateWithLifecycle()

            QuestionAnswerPairsQuestionScreen(
                screenPadding = screenPadding,
                questions = questions,
                questionMaterials = questionViewModel.materials,
                answers = answers,
                onQuestionSelect = { id ->
                    questionViewModel.onQuestionSelect(id)
                },
                onAnswerSelect = { id ->
                    questionViewModel.onAnswerSelect(id)
                },
                checkRequestState = checkRequestState,
                continueButtonEnabled = continueButtonEnabled,
                onContinueButtonClick = {
                    questionViewModel.getQuestionWithCheckResult()
                        ?.let(lessonViewModel::processQuestionCheckResult)
                        ?.let {
                            if (it) lessonProgressViewModel.incrementProgression()
                        }
                    navViewModel.navigateToNextQuestionOrResultsScreen(
                        navController = navController,
                        nextQuestionScreen = lessonViewModel.processToNextQuestion(),
                        onResetLessonProgression = lessonProgressViewModel::resetProgression
                    )
                    lessonProgressViewModel.incrementProgression()
                }
            )
        }
        composable<LessonSessionScreens.LessonResults> { backStack ->
            val courseContext = koinInject<CourseContext>()
            val viewModel = backStack.sharedKoinNavViewModel<LessonResultsViewModel>(navController)

            val lessonStats by viewModel.lessonStats.collectAsStateWithLifecycle()
            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            LaunchedEffect(true) {
                viewModel.finishSession()
            }

            LessonResultsScreen(
                screenPadding = screenPadding,
                requestState = requestState,
                lessonStatsUiState = lessonStats,
                onContinueButtonClick = {
                    navViewModel.popBackStack(
                        navController = navController,
                        screen = CourseScreens.Lessons(sectionId = courseContext.getSectionId())
                    )
                },
                onErrorClose = {
                    navViewModel.popBackStack(
                        navController = navController,
                        screen = CourseScreens.Lessons(sectionId = courseContext.getSectionId())
                    )
                }
            )
        }
    }
}