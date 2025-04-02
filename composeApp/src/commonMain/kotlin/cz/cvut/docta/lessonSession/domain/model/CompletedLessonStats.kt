package cz.cvut.docta.lessonSession.domain.model

data class CompletedLessonStats(
    val percentage: Double,
    val points: Double,
    val mistakeCount: Int
)
