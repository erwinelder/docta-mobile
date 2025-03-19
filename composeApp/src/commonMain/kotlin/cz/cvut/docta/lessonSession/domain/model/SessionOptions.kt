package cz.cvut.docta.lessonSession.domain.model

import cz.cvut.docta.lesson.domain.model.LessonDifficulty

sealed class SessionOptions {
    abstract val lessonId: Int

    data class Default(
        override val lessonId: Int,
        val difficulty: LessonDifficulty,
        val matchAllTags: Boolean
    ) : SessionOptions()

    data class StepByStep(
        override val lessonId: Int
    ) : SessionOptions()

}