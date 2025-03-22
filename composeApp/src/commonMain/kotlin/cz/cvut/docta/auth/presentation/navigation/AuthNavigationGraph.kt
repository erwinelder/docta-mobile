package cz.cvut.docta.auth.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import cz.cvut.docta.auth.presentation.model.AuthSuccessScreenType
import cz.cvut.docta.auth.presentation.screen.AuthSuccessScreen
import cz.cvut.docta.auth.presentation.screen.EmailVerificationScreen
import cz.cvut.docta.auth.presentation.screen.ProfileScreen
import cz.cvut.docta.auth.presentation.screen.SignInScreen
import cz.cvut.docta.auth.presentation.screen.SignOutScreen
import cz.cvut.docta.auth.presentation.screen.SignUpScreen
import cz.cvut.docta.auth.presentation.viewmodel.ProfileViewModel
import cz.cvut.docta.auth.presentation.viewmodel.SignInViewModel
import cz.cvut.docta.auth.presentation.viewmodel.SignOutViewModel
import cz.cvut.docta.auth.presentation.viewmodel.SignUpViewModel
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.navigation.sharedKoinNavViewModel
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import cz.cvut.docta.core.utils.takeActionIf
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.authGraph(
    startDestination: AuthScreens,
    navController: NavHostController,
    navViewModel: NavViewModel,
    screenPadding: PaddingValues
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
            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            val coroutineScope = rememberCoroutineScope()
            var job by remember { mutableStateOf<Job?>(null) }

            SignInScreen(
                screenPadding = screenPadding,
                emailState = emailState,
                onEmailChange = viewModel::updateAndValidateEmail,
                passwordState = passwordState,
                onPasswordChange = viewModel::updateAndValidatePassword,
                signInIsAllowed = signInIsAllowed,
                onSignIn = {
                    job = coroutineScope.launch {
                        if (viewModel.signIn()) navViewModel.navigate(
                            navController = navController,
                            screen = AuthScreens.ResultSuccess(
                                screenType = AuthSuccessScreenType.SignIn.name
                            )
                        )
                    }
                },
                onNavigateToSignUpScreen = {
                    navViewModel.popBackStackAndNavigate(
                        navController = navController,
                        screen = AuthScreens.SignUp(email = emailState.fieldText)
                    )
                },
                requestState = requestState,
                onCancelRequest = {
                    job?.cancel()
                    job = null
                },
                onCloseResult = viewModel::resetResultState
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
            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            val coroutineScope = rememberCoroutineScope()
            var job by remember { mutableStateOf<Job?>(null) }

            SignUpScreen(
                screenPadding = screenPadding,
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
                    job = coroutineScope.launch {
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
                requestState = requestState,
                onCancelRequest = {
                    job?.cancel()
                    job = null
                },
                onCloseResult = viewModel::resetResultState
            )
        }
        composable<AuthScreens.EmailVerification> { backStack ->
            val viewModel = backStack.sharedKoinNavViewModel<SignUpViewModel>(navController)

            val emailVerified by viewModel.emailVerified.collectAsStateWithLifecycle()
            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            LaunchedEffect(emailVerified) {
                if (emailVerified) {
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
                screenPadding = screenPadding,
                onNavigateBack = navController::popBackStack,
                emailVerified = emailVerified,
                onCheckEmailVerification = viewModel::checkEmailVerification,
                requestState = requestState,
                onCancelRequest = viewModel::cancelEmailVerificationCheck,
                onCloseResult = viewModel::checkEmailVerification
            )
        }
        composable<AuthScreens.ResultSuccess> { backStack ->
            val screenType = enumValueOf<AuthSuccessScreenType>(
                name = backStack.toRoute<AuthScreens.ResultSuccess>().screenType
            )

            AuthSuccessScreen(
                screenPadding = screenPadding,
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
        composable<AuthScreens.Profile> { backStack ->
            val userId = backStack.toRoute<AuthScreens.Profile>().userId
            val viewModel = koinViewModel<ProfileViewModel> {
                parametersOf(userId)
            }

            val userData by viewModel.userData.collectAsStateWithLifecycle()
            val nameState by viewModel.nameState.collectAsStateWithLifecycle()
            val userNameEditingState by viewModel.userNameEditingState.collectAsStateWithLifecycle()
            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            ProfileScreen(
                screenPadding = screenPadding,
                onNavigateToDeleteAccountScreen = {
                    navViewModel.navigate(
                        navController = navController, screen = AuthScreens.DeleteAccount
                    )
                },
                onNavigateToSignOutScreen = takeActionIf(userId == 0) {
                    navViewModel.navigate(
                        navController = navController, screen = AuthScreens.SignOut
                    )
                },
                userData = userData,
                nameState = nameState,
                userNameEditingState = userNameEditingState,
                onToggleUserNameEditingState = viewModel::toggleUserNameEditingState,
                onNameChange = viewModel::changeName,
                onSaveName = viewModel::saveName,
                requestState = requestState,
                onCancelRequest = navController::popBackStack,
                onCloseResult = navController::popBackStack
            )
        }
        composable<AuthScreens.SignOut> { backStack ->
            val viewModel = koinViewModel<SignOutViewModel>()

            val coroutineScope = rememberCoroutineScope()

            SignOutScreen(
                screenPadding = screenPadding,
                onNavigateBack = navController::popBackStack,
                onSignOut = {
                    coroutineScope.launch {
                        viewModel.signOut()
                        navViewModel.navigateAndPopUpTo(
                            navController = navController, screenToNavigateTo = AuthScreens.SignIn()
                        )
                    }
                }
            )
        }
        composable<AuthScreens.DeleteAccount> { backStack ->

        }
    }
}