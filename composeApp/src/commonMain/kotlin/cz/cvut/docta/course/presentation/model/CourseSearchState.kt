package cz.cvut.docta.course.presentation.model

import cz.cvut.docta.course.domain.model.CourseWithProgress

sealed class CourseSearchState {

    data object Prompt : CourseSearchState()

    data class Loading(val query: String) : CourseSearchState()

    data class SearchedCourse(
        val query: String,
        val course: CourseWithProgress?
    ) : CourseSearchState()

}