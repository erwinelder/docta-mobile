package cz.cvut.docta.lessonSession.presentation.model

import androidx.compose.runtime.Stable
import cz.cvut.docta.SharedRes
import cz.cvut.docta.lessonSession.domain.model.CompletedLessonStats
import dev.icerock.moko.resources.StringResource

@Stable
data class LessonStatsUiState(
    val firstDigit: String?,
    val secondDigit: String,
    val thirdDigit: String,
    val digitCount: Int,
    val messageRes: StringResource,
    val points: Double,
    val mistakeCount: Int
) {

    companion object {

        fun fromStats(stats: CompletedLessonStats): LessonStatsUiState {
            val digits = stats.percentage.toInt().toString().toCharArray().map { it.toString() }
            val finalDigits = if (digits.size == 3) digits else listOf(null) + digits

            return LessonStatsUiState(
                firstDigit = finalDigits[0],
                secondDigit = finalDigits[1] ?: "",
                thirdDigit = finalDigits[2] ?: "",
                digitCount = digits.size,
                messageRes = getMessageResByStats(stats = stats),
                points = stats.points,
                mistakeCount = stats.mistakeCount
            )
        }

        private fun getMessageResByStats(stats: CompletedLessonStats): StringResource {
            return when (stats.percentage) {
                100.0 -> SharedRes.strings.lesson_results_perfect
                in 85.0..100.0 -> SharedRes.strings.lesson_results_excellent
                in 50.0..85.0 -> SharedRes.strings.lesson_results_good
                else -> SharedRes.strings.lesson_results_completed
            }
        }

    }

}