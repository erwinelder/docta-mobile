package cz.cvut.docta.lessonSession.domain.model

sealed class SessionOptions {
    abstract val lessonId: Int

    data class Default(
        override val lessonId: Int
    ) : SessionOptions()

    data class StepByStep(
        override val lessonId: Int
    ) : SessionOptions()

}