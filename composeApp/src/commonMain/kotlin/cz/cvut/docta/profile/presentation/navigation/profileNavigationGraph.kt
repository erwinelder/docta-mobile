package cz.cvut.docta.profile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel

fun NavGraphBuilder.profileNavigationGraph(
    navController: NavHostController,
    navViewModel: NavViewModel
) {
    navigation<MainScreens.ProfileGraph>(
        startDestination = ProfileScreens.Profile
    ) {
        composable<ProfileScreens.Profile> {

        }
    }
}