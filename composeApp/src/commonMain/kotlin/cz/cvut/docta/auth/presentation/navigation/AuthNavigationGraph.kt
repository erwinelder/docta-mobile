package cz.cvut.docta.auth.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cz.cvut.docta.auth.presentation.model.AuthSuccessScreenType
import cz.cvut.docta.auth.presentation.model.EmailVerificationState
import cz.cvut.docta.auth.presentation.screen.AuthSuccessScreen
import cz.cvut.docta.auth.presentation.screen.EmailVerificationScreen
import cz.cvut.docta.auth.presentation.screen.SignInScreen
import cz.cvut.docta.auth.presentation.screen.SignUpScreen
import cz.cvut.docta.auth.presentation.viewmodel.SignInViewModel
import cz.cvut.docta.auth.presentation.viewmodel.SignUpViewModel
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.navigation.sharedKoinNavViewModel
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.authGraph(
    startDestination: AuthScreens,
    navController: NavHostController,
    navViewModel: NavViewModel
) {
    navigation<MainScreens.AuthGraph>(
        startDestination = startDestination
    ) {
        composable<AuthScreens.SignIn> { backStack ->
            val email = backStack.toRoute<AuthScreens.SignUp>().email

            val viewModel = koinViewModel<SignInViewModel> {
                parametersOf(email)
            }

            val emailState by viewModel.emailState.collectAsStateWithLifecycle()
            val passwordState by viewModel.passwordState.collectAsStateWithLifecycle()
            val signInIsAllowed by viewModel.signInIsAllowed.collectAsStateWithLifecycle()
            val resultState by viewModel.resultState.collectAsStateWithLifecycle()

            val coroutineScope = rememberCoroutineScope()

            SignInScreen(
                emailState = emailState,
                onEmailChange = viewModel::updateAndValidateEmail,
                passwordState = passwordState,
                onPasswordChange = viewModel::updateAndValidatePassword,
                signInIsAllowed = signInIsAllowed,
                onSignIn = {
                    coroutineScope.launch {
                        if (viewModel.signIn()) navViewModel.navigate(
                            navController = navController,
                            screen = AuthScreens.ResultSuccess(
                                screenType = AuthSuccessScreenType.SignIn.name
                            )
                        )
                    }
                },
                resultState = resultState,
                onResultReset = viewModel::resetResultState,
                onNavigateToSignUpScreen = {
                    navViewModel.popBackStackAndNavigate(
                        navController = navController,
                        screen = AuthScreens.SignUp(email = emailState.fieldText)
                    )
                }
            )
        }
        composable<AuthScreens.SignUp> { backStack ->
            val email = backStack.toRoute<AuthScreens.SignUp>().email

            val viewModel = backStack.sharedKoinNavViewModel<SignUpViewModel>(navController) {
                parametersOf(email)
            }

            val nameState by viewModel.nameState.collectAsStateWithLifecycle()
            val emailState by viewModel.emailState.collectAsStateWithLifecycle()
            val passwordState by viewModel.passwordState.collectAsStateWithLifecycle()
            val confirmPasswordState by viewModel.passwordConfirmationState.collectAsStateWithLifecycle()
            val signUpIsAllowed by viewModel.signUpIsAllowed.collectAsStateWithLifecycle()
            val resultState by viewModel.resultState.collectAsStateWithLifecycle()

            val coroutineScope = rememberCoroutineScope()

            SignUpScreen(
                nameState = nameState,
                onNameChange = viewModel::updateAndValidateName,
                emailState = emailState,
                onEmailChange = viewModel::updateAndValidateEmail,
                passwordState = passwordState,
                onPasswordChange = viewModel::updateAndValidatePassword,
                confirmPasswordState = confirmPasswordState,
                onConfirmPasswordChange = viewModel::updateAndValidateConfirmPassword,
                signUpIsAllowed = signUpIsAllowed,
                onSignUp = {
                    coroutineScope.launch {
                        if (!viewModel.signUp()) return@launch
                        viewModel.resetResultState()
                        navViewModel.navigate(
                            navController = navController, screen = AuthScreens.EmailVerification
                        )
                    }
                },
                onNavigateToSignInScreen = {
                    navViewModel.popBackStackAndNavigate(
                        navController = navController,
                        screen = AuthScreens.SignIn(email = emailState.fieldText)
                    )
                },
                resultState = resultState,
                onResultReset = viewModel::resetResultState
            )
        }
        composable<AuthScreens.EmailVerification> { backStack ->
            val viewModel = backStack.sharedKoinNavViewModel<SignUpViewModel>(navController)

            val emailVerificationState by viewModel.emailVerificationState.collectAsStateWithLifecycle()

            LaunchedEffect(emailVerificationState) {
                if (emailVerificationState == EmailVerificationState.Verified) {
                    navViewModel.navigateToScreenPoppingToStartDestination(
                        navController = navController,
                        navBackStackEntry = backStack,
                        screen = AuthScreens.ResultSuccess(
                            screenType = AuthSuccessScreenType.SignUp.name
                        )
                    )
                }
            }

            EmailVerificationScreen(
                onNavigateBack = navController::popBackStack,
                emailVerificationState = emailVerificationState,
                onCheckEmailVerification = viewModel::checkEmailVerification,
                onCancelEmailVerificationCheck = viewModel::cancelEmailVerificationCheck
            )
        }
        composable<AuthScreens.ResultSuccess> { backStack ->
            val screenType = enumValueOf<AuthSuccessScreenType>(
                name = backStack.toRoute<AuthScreens.ResultSuccess>().screenType
            )

            AuthSuccessScreen(
                screenType = screenType,
                onContinueButtonClick = {
                    navViewModel.navigateToScreenPoppingToStartDestination(
                        navController = navController,
                        navBackStackEntry = backStack,
                        screen = MainScreens.CoursesGraph
                    )
                }
            )
        }
        composable<AuthScreens.Profile> {

        }
        composable<AuthScreens.DeleteAccount> { backStack ->

        }
    }
}