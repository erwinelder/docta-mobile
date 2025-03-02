package cz.cvut.docta.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.lesson.presentation.navigation.LessonScreens

class NavViewModel : ViewModel() {

    fun <T : Any> navigate(
        navController: NavController,
        screen: T,
        launchSingleTop: Boolean = true
    ) {
        navController.navigate(screen) {
            this.launchSingleTop = launchSingleTop
        }
    }

    fun <N : Any, P : Any> navigateAndPopUpTo(
        navController: NavController,
        screenToNavigateTo: N,
        screenToPopUpTo: P,
        inclusive: Boolean = true
    ) {
        navController.navigate(screenToNavigateTo) {
            launchSingleTop = true
            popUpTo(screenToPopUpTo) {
                this.inclusive = inclusive
            }
        }
    }

    fun <N : Any> navigateAndPopUpTo(
        navController: NavController,
        screenToNavigateTo: N,
        inclusive: Boolean = true
    ) {
        navigateAndPopUpTo(
            navController = navController,
            screenToNavigateTo = screenToNavigateTo,
            screenToPopUpTo = screenToNavigateTo,
            inclusive = inclusive
        )
    }


    fun navigateToNextQuestionOrResultsScreen(
        navController: NavController,
        nextQuestionScreen: LessonScreens?,
        onResetLessonProgression: () -> Unit
    ) {
        navController.navigate(nextQuestionScreen ?: LessonScreens.LessonResults) {
            popUpTo<MainScreens.LessonGraph>()
            launchSingleTop = true
        }
        if (nextQuestionScreen == null) {
            onResetLessonProgression()
        }
    }

}