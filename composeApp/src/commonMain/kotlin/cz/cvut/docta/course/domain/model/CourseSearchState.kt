package cz.cvut.docta.course.domain.model

sealed class CourseSearchState {

    data object Prompt : CourseSearchState()

    data class Loading(val query: String) : CourseSearchState()

    data class SearchedCourse(val query: String, val course: Course?) : CourseSearchState()

}