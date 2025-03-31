package cz.cvut.docta.core.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.cvut.docta.achievement.presentation.navigation.achievementNavigationGraph
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.auth.presentation.navigation.AuthScreens
import cz.cvut.docta.auth.presentation.navigation.authGraph
import cz.cvut.docta.core.presentation.component.container.BottomNavBar
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.utils.anyScreenInHierarchyIs
import cz.cvut.docta.core.presentation.utils.currentScreenIs
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import cz.cvut.docta.course.presentation.navigation.courseNavigationGraph
import cz.cvut.docta.courseEditing.presentation.navigation.courseManagementNavigationGraph
import cz.cvut.docta.leaderboard.presentation.screen.LeaderboardScreen
import cz.cvut.docta.leaderboard.presentation.viewmodel.LeaderboardViewModel
import cz.cvut.docta.lesson.presentation.component.LessonProgressBar
import cz.cvut.docta.lesson.presentation.navigation.lessonNavigationGraph
import cz.cvut.docta.lesson.presentation.viewmodel.LessonProgressViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainApplicationContent(
    userContext: UserContext = koinInject(),
    navController: NavHostController = rememberNavController()
) {
    val navViewModel = koinViewModel<NavViewModel>()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isBottomBarVisible by remember(navBackStackEntry) {
        derivedStateOf {
            navViewModel.needToDisplayBottomNavBar(navBackStackEntry = navBackStackEntry)
        }
    }
    val systemBarPadding = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()

    val mainStartDestination: MainScreens by remember {
        derivedStateOf {
            if (userContext.userId == 0) MainScreens.Auth else MainScreens.Courses
        }
    }
    val authStartDestination: AuthScreens by remember {
        derivedStateOf {
            if (userContext.userId == 0) AuthScreens.SignIn() else AuthScreens.Profile()
        }
    }

    val lessonProgressViewModel = koinViewModel<LessonProgressViewModel>()
    val lessonProgression by lessonProgressViewModel.progression.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()),
        topBar = {
            LessonProgressBar(progression = lessonProgression)
        },
        bottomBar = {
            BottomNavBar(
                isVisible = isBottomBarVisible,
                padding = PaddingValues(
                    bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
                ),
                anyScreenInHierarchyIsScreenProvider = navBackStackEntry::anyScreenInHierarchyIs,
                currentScreenIsScreenProvider = navBackStackEntry::currentScreenIs,
                onNavigateToScreen = { screen: Any ->
                    navViewModel.navigateToScreenPoppingToStartDestination(
                        navController = navController,
                        navBackStackEntry = navBackStackEntry,
                        screen = screen
                    )
                }
            )
        },
        backgroundColor = Color.Transparent
    ) { screenPadding ->
        NavHost(
            navController = navController,
            startDestination = mainStartDestination
        ) {
            courseNavigationGraph(
                navController = navController,
                navViewModel = navViewModel,
                screenPadding = screenPadding
            )
            lessonNavigationGraph(
                navController = navController,
                navViewModel = navViewModel,
                screenPadding = screenPadding,
                lessonProgressViewModel = lessonProgressViewModel
            )
            courseManagementNavigationGraph(
                navController = navController,
                navViewModel = navViewModel
            )
            achievementNavigationGraph(
                navController = navController,
                navViewModel = navViewModel,
                screenPadding = screenPadding
            )
            authGraph(
                startDestination = authStartDestination,
                navController = navController,
                navViewModel = navViewModel,
                screenPadding = PaddingValues(bottom = systemBarPadding)
            )
            composable<MainScreens.Leaderboard> {
                val viewModel = koinViewModel<LeaderboardViewModel>()

                val leaders by viewModel.leaders.collectAsStateWithLifecycle()

                LeaderboardScreen(
                    screenPadding = screenPadding,
                    leaders = leaders
                )
            }
        }
    }
}