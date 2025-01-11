package cz.cvut.docta.question.data.local.model

sealed class LessonQuestionsQueryOptions(
    open val courseCode: String,
    open val lessonId: Long
) {

    data class Default(
        override val courseCode: String,
        override val lessonId: Long,
        val difficulty: String,
        val matchAllTags: Boolean
    ) : LessonQuestionsQueryOptions(courseCode, lessonId)

    data class StepByStep(
        override val courseCode: String,
        override val lessonId: Long,
    ) : LessonQuestionsQueryOptions(courseCode, lessonId)

}