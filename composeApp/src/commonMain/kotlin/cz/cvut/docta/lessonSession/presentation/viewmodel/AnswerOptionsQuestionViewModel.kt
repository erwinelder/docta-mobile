package cz.cvut.docta.lessonSession.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInput
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerOptionUiState
import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCheckResult
import cz.cvut.docta.lessonSession.presentation.model.QuestionAndAnswersWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AnswerOptionsQuestionViewModel(
    private val question: QuestionAndAnswersWrapper.AnswerOptions
) : ViewModel() {

    val questionText = question.question.text
    val isMultipleChoice = question.question.isMultipleChoice

    private val _selectedOptionIds = MutableStateFlow<List<Long>>(emptyList())
    val selectedOptionIds = _selectedOptionIds.asStateFlow()

    private val _options = MutableStateFlow(
        question.question.options.map {
            AnswerOptionUiState(id = it.id, text = it.text, isSelected = false)
        }
    )
    val options = _options.asStateFlow()

    fun onSelectionChanged(selectedIds: List<Long>) {
        _selectedOptionIds.value = selectedIds
        _options.update {
            it.map { option ->
                option.copy(isSelected = selectedIds.contains(option.id))
            }
        }
    }

    val checkIsAllowed = _selectedOptionIds
        .combine(_options) { selected, _ -> selected.isNotEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )

    private val _checkResult = MutableStateFlow<QuestionCheckResult?>(null)
    val checkResult = _checkResult.asStateFlow()

    private fun setCheckResult(result: QuestionCheckResult) {
        _checkResult.value = result
    }

    fun checkAnswer(): QuestionWithCheckResult {
        val selectedIds = selectedOptionIds.value
        val result = question.checkAnswer(selectedIds)

        _checkResult.value = result

        return QuestionWithCheckResult(
            question = question,
            result = result
        )
    }
}
