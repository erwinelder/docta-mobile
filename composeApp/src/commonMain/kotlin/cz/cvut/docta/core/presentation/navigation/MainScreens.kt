package cz.cvut.docta.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface MainScreens {

    @Serializable
    data object Courses : MainScreens

    @Serializable
    data class CourseSections(val courseCode: String) : MainScreens

}