package cz.cvut.docta.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.utils.currentScreenIsAnyOf
import cz.cvut.docta.course.presentation.navigation.CourseScreens
import cz.cvut.docta.lesson.presentation.navigation.LessonScreens
import cz.cvut.docta.profile.presentation.navigation.ProfileScreens

class NavViewModel : ViewModel() {

    fun needToDisplayBottomNavBar(
        appIsSetUp: Boolean,
        navBackStackEntry: NavBackStackEntry?
    ): Boolean {
        return appIsSetUp && navBackStackEntry.currentScreenIsAnyOf(
            CourseScreens.Courses, CourseScreens.AddNewCourse, CourseScreens.Sections(),
            CourseScreens.Lessons(),
            ProfileScreens.Profile
        )
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

    fun navigateToScreenPoppingToStartDestination(
        navController: NavController,
        navBackStackEntry: NavBackStackEntry?,
        screen: MainScreens
    ) {
        navController.navigate(screen) {
            navController.graph.findStartDestination().route?.let {
                popUpTo(it) {
                    inclusive = false
                }
            }
            launchSingleTop = true
        }
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