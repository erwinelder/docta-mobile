package cz.cvut.docta.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface MainScreens {

    @Serializable
    data object Courses : MainScreens

    @Serializable
    data class CourseManagement(val courseCode: String) : MainScreens

    @Serializable
    data object LessonSession : MainScreens

    @Serializable
    data object Leaderboard : MainScreens

    @Serializable
    data object Achievements : MainScreens

    @Serializable
    data object Auth : MainScreens

}