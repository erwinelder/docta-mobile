package cz.cvut.docta.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.ParametersDefinition

@OptIn(KoinExperimentalAPI::class)
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinNavViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinNavViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinNavViewModel(viewModelStoreOwner = parentEntry)
}

@OptIn(KoinExperimentalAPI::class)
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinNavViewModel(
    navController: NavController,
    noinline parameters: ParametersDefinition? = null
): T {
    val navGraphRoute = destination.parent?.route ?: return koinNavViewModel(parameters = parameters)
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinNavViewModel(viewModelStoreOwner = parentEntry, parameters = parameters)
}