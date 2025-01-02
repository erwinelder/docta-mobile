package cz.cvut.docta.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface MainScreens {

    @Serializable
    data object Courses : MainScreens

    @Serializable
    data class CourseEdit(val courseCode: String) : MainScreens

    @Serializable
    data class CourseSections(val courseCode: String) : MainScreens

    @Serializable
    data class SectionLessons(val sectionId: Long): MainScreens

    @Serializable
    data class Lesson(val lessonId: Long): MainScreens

    @Serializable
    data object LessonResults : MainScreens

}