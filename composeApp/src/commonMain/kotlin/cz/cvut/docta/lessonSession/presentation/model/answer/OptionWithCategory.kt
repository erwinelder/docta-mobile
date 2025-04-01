package cz.cvut.docta.lessonSession.presentation.model.answer

import androidx.compose.runtime.Stable
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText

@Stable
data class OptionWithCategory(
    val optionId: Long,
    val optionText: String,
    val category: AnswerText? = null
) {

    fun getOptionIdWithCategoryIdOrNull(): Pair<Long, Long>? {
        return category?.let { optionId to it.id }
    }

}