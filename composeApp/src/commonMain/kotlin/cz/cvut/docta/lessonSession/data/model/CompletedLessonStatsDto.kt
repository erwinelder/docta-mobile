package cz.cvut.docta.lessonSession.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CompletedLessonStatsDto(
    val percentage: Double,
    val points: Double,
    val mistakeCount: Int
)
