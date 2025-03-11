package cz.cvut.docta.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface MainScreens {

    @Serializable
    data object CoursesGraph : MainScreens

    @Serializable
    data class CourseManagementGraph(val courseCode: String) : MainScreens

    @Serializable
    data class LessonGraph(val lessonId: Long) : MainScreens

    @Serializable
    data object Leaderboard : MainScreens

    @Serializable
    data object ProfileGraph : MainScreens

}