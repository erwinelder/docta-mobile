package cz.cvut.docta.lesson.domain.model

sealed class Lesson(
    open val id: Long,
    open val name: String,
    open val statistics: UserLessonStats
) {

    data class Default(
        override val id: Long,
        override val name: String,
        override val statistics: UserLessonStats,
        val difficulty: LessonDifficulty,
    ) : Lesson(id, name, statistics)

    data class StepByStep(
        override val id: Long,
        override val name: String,
        override val statistics: UserLessonStats,
        val description: String,
        val difficulty: LessonDifficulty,
    ) : Lesson(id, name, statistics)

    data class Test(
        override val id: Long,
        override val name: String,
        override val statistics: UserLessonStats,
    ) : Lesson(id, name, statistics)

}