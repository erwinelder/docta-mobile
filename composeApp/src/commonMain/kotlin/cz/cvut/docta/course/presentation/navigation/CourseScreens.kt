package cz.cvut.docta.course.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface CourseScreens {

    @Serializable
    data object Courses : CourseScreens

    @Serializable
    data object AddNewCourse : CourseScreens

    @Serializable
    data class Sections(val courseCode: String = "") : CourseScreens

    @Serializable
    data class Lessons(val sectionId: Long = 0) : CourseScreens

}