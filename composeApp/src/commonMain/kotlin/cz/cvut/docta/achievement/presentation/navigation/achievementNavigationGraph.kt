package cz.cvut.docta.achievement.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import cz.cvut.docta.achievement.presentation.screen.AchievementDetailsScreen
import cz.cvut.docta.achievement.presentation.screen.AchievementsScreen
import cz.cvut.docta.achievement.presentation.viewmodel.AchievementsViewModel
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.navigation.sharedKoinNavViewModel
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel

fun NavGraphBuilder.achievementNavigationGraph(
    navController: NavController,
    navViewModel: NavViewModel,
    screenPadding: PaddingValues
) {
    navigation<MainScreens.AchievementsGraph>(
        startDestination = AchievementScreens.Achievements
    ) {
        composable<AchievementScreens.Achievements> { backStack ->
            val viewModel = backStack.sharedKoinNavViewModel<AchievementsViewModel>(navController)

            val achievements by viewModel.achievements.collectAsStateWithLifecycle()

            AchievementsScreen(
                achievements = achievements,
                onAchievementClick = { achievement ->
                    viewModel.applyAchievement(achievement = achievement)

                    navViewModel.navigate(
                        navController = navController,
                        screen = AchievementScreens.AchievementDetails
                    )
                }
            )
        }
        composable<AchievementScreens.AchievementDetails> { backStack ->
            val viewModel = backStack.sharedKoinNavViewModel<AchievementsViewModel>(navController)

            val achievement by viewModel.achievement.collectAsStateWithLifecycle()

            // TODO-ACHIEVEMENTS: Add not found screen

            // AchievementDetailsScreen(achievement = achievement)

            // TODO-ACHIEVEMENTS: Replace this
            achievement?.let {
                AchievementDetailsScreen(
                    achievement = it,
                    onClose = { navController.popBackStack() }
                )
            }

        }
    }
}