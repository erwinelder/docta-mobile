package cz.cvut.docta.question.data.model

sealed class LessonQuestionsQueryOptions(
    open val lessonId: Long
) {

    data class Default(
        override val lessonId: Long,
        val difficulty: String,
        val matchAllTags: Boolean,
        val courseCode: String
    ) : LessonQuestionsQueryOptions(lessonId)

    data class StepByStep(
        override val lessonId: Long
    ) : LessonQuestionsQueryOptions(lessonId)

}