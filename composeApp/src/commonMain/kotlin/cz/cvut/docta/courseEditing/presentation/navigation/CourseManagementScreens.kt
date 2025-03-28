package cz.cvut.docta.courseEditing.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface CourseManagementScreens {

    @Serializable
    data class CourseEditing(val courseCode: String) : CourseManagementScreens

    @Serializable
    data class SectionEditing(val sectionId: Int) : CourseManagementScreens

    @Serializable
    data class LessonEditing(val lessonId: Int) : CourseManagementScreens

}