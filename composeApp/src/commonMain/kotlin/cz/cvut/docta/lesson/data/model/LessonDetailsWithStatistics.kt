package cz.cvut.docta.lesson.data.model

sealed class LessonDetailsWithStatistics(
    open val sectionId: Long,
    open val id: Long,
    open val orderNum: Int,
    open val name: String,
    open val difficulty: String,
    open val statistics: LessonDetailsStatistics
) {

    data class DefaultLesson(
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val difficulty: String,
        override val statistics: LessonDetailsStatistics,
        val type: String,
        val matchAllTags: Boolean
    ) : LessonDetailsWithStatistics(sectionId, id, orderNum, name, difficulty, statistics)

    data class StepByStepLesson(
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val statistics: LessonDetailsStatistics,
        override val difficulty: String,
        val description: String
    ) : LessonDetailsWithStatistics(sectionId, id, orderNum, name, difficulty, statistics)

}