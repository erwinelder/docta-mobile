package cz.cvut.docta.course.domain.model

data class Course(
    val code: String,
    val locale: CourseLocale,
    val name: String
)
