package cz.cvut.docta.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import cz.cvut.docta.auth.presentation.navigation.AuthScreens
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.utils.currentScreenIsAnyOf
import cz.cvut.docta.course.presentation.navigation.CourseScreens
import cz.cvut.docta.lesson.presentation.navigation.LessonSessionScreens

class NavViewModel : ViewModel() {

    fun needToDisplayBottomNavBar(
        navBackStackEntry: NavBackStackEntry?
    ): Boolean {
        return navBackStackEntry.currentScreenIsAnyOf(
            CourseScreens.Courses, CourseScreens.AddNewCourse, CourseScreens.Sections(),
            CourseScreens.Lessons(),
            AuthScreens.Profile()
        )
    }


    fun <T : Any> popBackStack(
        navController: NavController,
        screen: T,
        inclusive: Boolean = false
    ) {
        navController.popBackStack(route = screen, inclusive = inclusive)
    }

    fun <T : Any> navigate(
        navController: NavController,
        screen: T,
        launchSingleTop: Boolean = true
    ) {
        navController.navigate(screen) {
            this.launchSingleTop = launchSingleTop
        }
    }

    fun <T : Any> popBackStackAndNavigate(
        navController: NavController,
        screen: T,
        launchSingleTop: Boolean = true
    ) {
        navController.popBackStack()
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
        navController.navigate(screenToNavigateTo) {
            launchSingleTop = true
            popUpTo(0) {
                this.inclusive = inclusive
            }
        }
    }

    fun navigateToScreenPoppingToStartDestination(
        navController: NavController,
        navBackStackEntry: NavBackStackEntry?,
        screen: Any,
        inclusive: Boolean = false
    ) {
        navController.navigate(screen) {
            navController.graph.findStartDestination().route?.let {
                popUpTo(it) {
                    this.inclusive = inclusive
                }
            }
            launchSingleTop = true
        }
    }


    fun navigateToNextQuestionOrResultsScreen(
        navController: NavController,
        nextQuestionScreen: LessonSessionScreens?,
        onResetLessonProgression: () -> Unit
    ) {
        navController.navigate(nextQuestionScreen ?: LessonSessionScreens.LessonResults) {
            popUpTo<MainScreens.LessonSession>()
            launchSingleTop = true
        }
        if (nextQuestionScreen == null) {
            onResetLessonProgression()
        }
    }

}