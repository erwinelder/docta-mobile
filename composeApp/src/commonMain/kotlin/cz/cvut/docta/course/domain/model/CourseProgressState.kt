package cz.cvut.docta.course.domain.model

sealed class CourseProgressState {
    data class Done(
        val id: String,
        val name: String,
        val statistics: CourseStatistics
    ) : CourseProgressState()

    data class InProgress(
        val id: String,
        val name: String,
        val statistics: CourseStatistics
    ) : CourseProgressState()

    data class Locked(
        val id: String,
        val name: String
    ) : CourseProgressState()
}
