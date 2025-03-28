package cz.cvut.docta.course.domain.model

sealed class CourseWithProgress(
    open val code: String,
    open val locale: CourseLocale,
    open val name: String,
    open val sectionCount: Int
) {

    data class NotStarted(
        override val code: String,
        override val locale: CourseLocale,
        override val name: String,
        override val sectionCount: Int
    ) : CourseWithProgress(code, locale, name, sectionCount)

    data class InProgress(
        override val code: String,
        override val locale: CourseLocale,
        override val name: String,
        override val sectionCount: Int,
        val completed: Int
    ) : CourseWithProgress(code, locale, name, sectionCount)

    data class Completed(
        override val code: String,
        override val locale: CourseLocale,
        override val name: String,
        override val sectionCount: Int
    ) : CourseWithProgress(code, locale, name, sectionCount)

}