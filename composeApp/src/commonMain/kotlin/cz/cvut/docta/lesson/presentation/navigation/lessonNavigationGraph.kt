package cz.cvut.docta.lesson.presentation.navigation

import CategorizationQuestionScreen
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
import cz.cvut.docta.lesson.domain.model.LessonResults
import cz.cvut.docta.lesson.presentation.screen.LessonResultsScreen
import cz.cvut.docta.lesson.presentation.utils.getLessonScreenToNavigateTo
import cz.cvut.docta.lesson.presentation.viewmodel.LessonProgressViewModel
import cz.cvut.docta.lesson.presentation.viewmodel.LessonViewModel
import cz.cvut.docta.lessonSession.mapper.getSessionOptions
import cz.cvut.docta.lessonSession.presentation.model.QuestionAndAnswersWrapper
import cz.cvut.docta.lessonSession.presentation.model.question.CategoryUiState
import cz.cvut.docta.lessonSession.presentation.screen.AnswerOptionsQuestionScreen
import cz.cvut.docta.lessonSession.presentation.screen.FillInBlanksQuestionScreen
import cz.cvut.docta.lessonSession.presentation.screen.OpenAnswerQuestionScreen
import cz.cvut.docta.lessonSession.presentation.screen.QuestionAnswerPairsQuestionScreen
import cz.cvut.docta.lessonSession.presentation.viewmodel.AnswerOptionsQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.CategorizationQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.FillInBlanksQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.OpenAnswerQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.QuestionAnswerPairsQuestionViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.lessonNavigationGraph(
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
                return@composable // TODO
            }

            val viewModel = backStack.sharedKoinNavViewModel<LessonViewModel>(navController)
            LaunchedEffect(sessionOptions) {
                val questionsCount = viewModel.fetchQuestions(sessionOptions = sessionOptions)
                lessonProgressViewModel.setProgression(questionCount = questionsCount)

                viewModel.getNextQuestionOrNull()?.getLessonScreenToNavigateTo()?.let { nextScreen ->
                    navViewModel.navigate(navController, nextScreen)
                }
            }


        }
        composable<LessonSessionScreens.OpenAnswerQuestion> { backStack ->
            val lessonViewModel = backStack.sharedKoinNavViewModel<LessonViewModel>(navController)
            val questionViewModel = koinViewModel<OpenAnswerQuestionViewModel> {
                parametersOf(
                    lessonViewModel.getNextQuestionAs<QuestionAndAnswersWrapper.OpenAnswer>()
                )
            }

            val answerInput by questionViewModel.answerInput.collectAsStateWithLifecycle()
            val checkIsAllowed by questionViewModel.checkIsAllowed.collectAsStateWithLifecycle()
            val checkResult by questionViewModel.checkResult.collectAsStateWithLifecycle()

            OpenAnswerQuestionScreen(
                screenPadding = screenPadding,
                questionText = questionViewModel.questionText,
                questionMaterials = questionViewModel.materials,
                answerInput = answerInput,
                onAnswerChange = questionViewModel::onAnswerInputChange,
                checkIsAllowed = checkIsAllowed,
                checkResult = checkResult,
                onCheckButtonClick = {
                    lessonViewModel.processQuestionCheckResult(questionViewModel.checkAnswer())
                    lessonProgressViewModel.incrementProgression()
                },
                onContinueButtonClick = {
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
                    lessonViewModel.getNextQuestionAs<QuestionAndAnswersWrapper.FillInBlanks>()
                )
            }

            val blanksAnswers by questionViewModel.blanksAnswers.collectAsStateWithLifecycle()
            val checkIsAllowed by questionViewModel.checkIsAllowed.collectAsStateWithLifecycle()
            val checkResult by questionViewModel.checkResult.collectAsStateWithLifecycle()

            FillInBlanksQuestionScreen(
                screenPadding = screenPadding,
                questionUnits = questionViewModel.questionUnits,
                questionMaterials = questionViewModel.questionMaterials,
                blanksAnswers = blanksAnswers,
                onBlankAnswerChange = questionViewModel::onBlankAnswerChange,
                checkIsAllowed = checkIsAllowed,
                checkResult = checkResult,
                onCheckButtonClick = {
                    lessonViewModel.processQuestionCheckResult(questionViewModel.checkAnswers())
                    lessonProgressViewModel.incrementProgression()
                },
                onContinueButtonClick = {
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
                    lessonViewModel.getNextQuestionAs<QuestionAndAnswersWrapper.AnswerOptions>()
                )
            }

            val options by questionViewModel.options.collectAsStateWithLifecycle()
            val checkIsAllowed by questionViewModel.checkIsAllowed.collectAsStateWithLifecycle()
            val checkResult by questionViewModel.checkResult.collectAsStateWithLifecycle()

            AnswerOptionsQuestionScreen(
                screenPadding = screenPadding,
                questionText = questionViewModel.questionText,
                questionMaterials = questionViewModel.questionMaterials,
                options = options,
                onOptionSelect = questionViewModel::onOptionSelect,
                checkIsAllowed = checkIsAllowed,
                checkResult = checkResult,
                onCheckButtonClick = {
                    questionViewModel.checkAnswer()?.let(lessonViewModel::processQuestionCheckResult)
                    lessonProgressViewModel.incrementProgression()
                },
                onContinueButtonClick = {
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
                    lessonViewModel.getNextQuestionAs<QuestionAndAnswersWrapper.Categorization>()
                )
            }

            val options by questionViewModel.options.collectAsStateWithLifecycle()
            val checkIsAllowed by questionViewModel.checkIsAllowed.collectAsStateWithLifecycle()
            val checkResult by questionViewModel.checkResult.collectAsStateWithLifecycle()


            val categories = questionViewModel.categories.map {
                CategoryUiState(id = it.id, name = it.text)
            }

            CategorizationQuestionScreen(
                screenPadding = screenPadding,
                questionText = questionViewModel.questionText,
                questionMaterials = questionViewModel.questionMaterials,
                options = options,
                categories = categories,
                onCategorySelect = questionViewModel::onCategorySelect,
                checkIsAllowed = checkIsAllowed,
                checkResult = checkResult,
                onCheckButtonClick = {
                    questionViewModel.checkAnswer()?.let { questionWithResult ->
                        lessonViewModel.processQuestionCheckResult(questionWithResult)
                    }
                    lessonProgressViewModel.incrementProgression()
                },
                onContinueButtonClick = {
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
                    lessonViewModel.getNextQuestionAs<QuestionAndAnswersWrapper.QuestionAnswerPairs>()
                )
            }

            val questions by questionViewModel.questions.collectAsStateWithLifecycle()
            val answers by questionViewModel.answers.collectAsStateWithLifecycle()
            val continueButtonEnabled by questionViewModel.continueButtonEnabled.collectAsStateWithLifecycle()

            QuestionAnswerPairsQuestionScreen(
                screenPadding = screenPadding,
                questions = questions,
                questionMaterials = questionViewModel.questionMaterials,
                answers = answers,
                onQuestionSelect = { id ->
                    questionViewModel.onQuestionSelect(id)
                },
                onAnswerSelect = {id ->
                    questionViewModel.onAnswerSelect(id)
                },
                continueButtonEnabled = continueButtonEnabled,
                onContinueButtonClick = {
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

            val viewModel = backStack.sharedKoinNavViewModel<LessonViewModel>(navController)

//            val lessonState by viewModel.lessonState.collectAsStateWithLifecycle()
//            val lessonResults = lessonState as? LessonState.Results
            val lessonResults = LessonResults(successRate = 50)

            LessonResultsScreen(
                results = lessonResults,
                onContinueButtonClick = {
                    navViewModel.popBackStack(
                        navController = navController,
                        screen = CourseScreens.Lessons(sectionId = courseContext.getSectionId())
                    )
                }
            )
        }
    }
}