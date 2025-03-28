package cz.cvut.docta.auth.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
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
import cz.cvut.docta.auth.presentation.screen.DeleteOwnAccountScreen
import cz.cvut.docta.auth.presentation.screen.DeleteUserAccountScreen
import cz.cvut.docta.auth.presentation.screen.EmailVerificationScreen
import cz.cvut.docta.auth.presentation.screen.ProfileScreen
import cz.cvut.docta.auth.presentation.screen.SignInScreen
import cz.cvut.docta.auth.presentation.screen.SignOutScreen
import cz.cvut.docta.auth.presentation.screen.SignUpScreen
import cz.cvut.docta.auth.presentation.viewmodel.DeleteOwnAccountViewModel
import cz.cvut.docta.auth.presentation.viewmodel.DeleteUserAccountViewModel
import cz.cvut.docta.auth.presentation.viewmodel.ProfileViewModel
import cz.cvut.docta.auth.presentation.viewmodel.SignInViewModel
import cz.cvut.docta.auth.presentation.viewmodel.SignOutViewModel
import cz.cvut.docta.auth.presentation.viewmodel.SignUpViewModel
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.navigation.sharedKoinNavViewModel
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
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
    navigation<MainScreens.Auth>(
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
                        viewModel.signIn()
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
                onSuccessClose = {
                    navViewModel.navigateAndPopUpTo(
                        navController = navController, screenToNavigateTo = MainScreens.Courses
                    )
                },
                onErrorClose = viewModel::resetResultState
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
                onErrorClose = viewModel::resetResultState
            )
        }
        composable<AuthScreens.EmailVerification> { backStack ->
            val viewModel = backStack.sharedKoinNavViewModel<SignUpViewModel>(navController)

            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            EmailVerificationScreen(
                screenPadding = screenPadding,
                onNavigateBack = navController::popBackStack,
                onCheckEmailVerification = viewModel::checkEmailVerification,
                requestState = requestState,
                onCancelRequest = viewModel::cancelEmailVerificationCheck,
                onSuccessClose = {
                    navViewModel.navigateAndPopUpTo(
                        navController = navController, screenToNavigateTo = MainScreens.Courses
                    )
                },
                onErrorClose = viewModel::resetResultState
            )
        }
        composable<AuthScreens.Profile> { backStack ->
            val userId = backStack.toRoute<AuthScreens.Profile>().userId
            val viewModel = koinViewModel<ProfileViewModel> {
                parametersOf(userId)
            }

            val userData by viewModel.userData.collectAsStateWithLifecycle()
            val nameEditingState by viewModel.nameEditingState.collectAsStateWithLifecycle()
            val nameState by viewModel.nameState.collectAsStateWithLifecycle()
            val roleEditingState by viewModel.roleEditingState.collectAsStateWithLifecycle()
            val roleState by viewModel.roleState.collectAsStateWithLifecycle()
            val requestState by viewModel.userDataRequestState.collectAsStateWithLifecycle()

            ProfileScreen(
                screenPadding = screenPadding,
                permissions = viewModel.permissions,
                onNavigateToDeleteAccountScreen = {
                    navViewModel.navigate(
                        navController = navController,
                        screen = if (userId == 0) {
                            AuthScreens.DeleteOwnAccount
                        } else {
                            AuthScreens.DeleteUserAccount(userId = userId)
                        }
                    )
                },
                onNavigateToSignOutScreen = {
                    navViewModel.navigate(
                        navController = navController, screen = AuthScreens.SignOut
                    )
                },
                userData = userData,

                nameEditingState = nameEditingState,
                onToggleNameEditingState = viewModel::toggleNameEditingState,
                onSaveName = viewModel::saveName,
                nameState = nameState,
                onNameChange = viewModel::changeName,

                roleEditingState = roleEditingState,
                onToggleRoleEditingState = viewModel::toggleRoleEditingState,
                onSaveRole = viewModel::saveRole,
                roleState = roleState,
                availableRoles = viewModel.availableRoles,
                onRoleSelect = viewModel::selectRole,

                userDataRequestState = requestState,
                onCancelUserDataRequest = navController::popBackStack,
                onUserDataFetchResult = navController::popBackStack
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
        composable<AuthScreens.DeleteOwnAccount> {
            val viewModel = koinViewModel<DeleteOwnAccountViewModel>()

            val passwordState by viewModel.passwordState.collectAsStateWithLifecycle()
            val requestState by viewModel.requestState.collectAsStateWithLifecycle()
            val allowDeleteAccount by viewModel.allowDeleteAccount.collectAsStateWithLifecycle()

            val coroutineScope = rememberCoroutineScope()
            var job by remember { mutableStateOf<Job?>(null) }

            DeleteOwnAccountScreen(
                screenPadding = screenPadding,
                onNavigateBack = navController::popBackStack,
                passwordState = passwordState,
                onPasswordChange = viewModel::updateAndValidatePassword,
                allowDeleteAccount = allowDeleteAccount,
                onDeleteAccount = {
                    job = coroutineScope.launch {
                        viewModel.deleteAccount()
                    }
                },
                requestState = requestState,
                onCancelRequest = {
                    job?.cancel()
                    job = null
                },
                onSuccessClose = {
                    navViewModel.navigateAndPopUpTo(
                        navController = navController, screenToNavigateTo = AuthScreens.SignIn()
                    )
                },
                onErrorClose = viewModel::resetResultState
            )
        }
        composable<AuthScreens.DeleteUserAccount> { backStack ->
            val userId = backStack.toRoute<AuthScreens.Profile>().userId
            val viewModel = koinViewModel<DeleteUserAccountViewModel> {
                parametersOf(userId)
            }

            val requestState by viewModel.requestState.collectAsStateWithLifecycle()

            val coroutineScope = rememberCoroutineScope()
            var job by remember { mutableStateOf<Job?>(null) }

            DeleteUserAccountScreen(
                screenPadding = screenPadding,
                onNavigateBack = navController::popBackStack,
                onDeleteAccount = {
                    job = coroutineScope.launch {
                        viewModel.deleteAccount()
                    }
                },
                requestState = requestState,
                onCancelRequest = {
                    job?.cancel()
                    job = null
                },
                onSuccessClose = {
                    navController.popBackStack()
                    navController.popBackStack()
                },
                onErrorClose = viewModel::resetResultState
            )
        }
    }
}