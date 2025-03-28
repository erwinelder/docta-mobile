package cz.cvut.docta.section.domain.model

sealed class SectionWithProgress(
    open val courseCode: String,
    open val id: Int,
    open val orderNum: Int,
    open val name: String,
    open val lessonCount: Int
) {

    data class NotStarted(
        override val courseCode: String,
        override val id: Int,
        override val orderNum: Int,
        override val name: String,
        override val lessonCount: Int
    ) : SectionWithProgress(courseCode, id, orderNum, name, lessonCount)

    data class InProgress(
        override val courseCode: String,
        override val id: Int,
        override val orderNum: Int,
        override val name: String,
        override val lessonCount: Int,
        val completed: Int
    ) : SectionWithProgress(courseCode, id, orderNum, name, lessonCount)

    data class Completed(
        override val courseCode: String,
        override val id: Int,
        override val orderNum: Int,
        override val name: String,
        override val lessonCount: Int
    ) : SectionWithProgress(courseCode, id, orderNum, name, lessonCount)

}