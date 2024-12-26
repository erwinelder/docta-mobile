package cz.cvut.docta.lesson.domain.model

sealed class Lesson(
    open val id: Long,
    open val name: String,
    open val statistics: LessonStatistics
) {

    data class Default(
        override val id: Long,
        override val name: String,
        override val statistics: LessonStatistics,
        val difficulty: LessonDifficulty,
    ) : Lesson(id, name, statistics)

    data class StepByStep(
        override val id: Long,
        override val name: String,
        override val statistics: LessonStatistics,
        val description: String,
        val difficulty: LessonDifficulty,
    ) : Lesson(id, name, statistics)

    data class Test(
        override val id: Long,
        override val name: String,
        override val statistics: LessonStatistics,
    ) : Lesson(id, name, statistics)

}