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


    private val _options = MutableStateFlow(
        question.question.options.map {
            AnswerOptionUiState(id = it.id, text = it.text, isSelected = false)
        }
    )
    val options = _options.asStateFlow()

    fun onOptionSelect(optionId: Long) {
        _options.update {
            it.map { option ->
                option.copy(isSelected = option.id == optionId)
            }
        }
    }

    private fun getSelectedOption(): Long? {
        return options.value.find { it.isSelected }?.id
    }


    val checkIsAllowed = combine(_options) { optionsArray ->
        val options = optionsArray[0]
        options.any { it.isSelected }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )


    private val _checkResult = MutableStateFlow<QuestionCheckResult?>(null)
    val checkResult = _checkResult.asStateFlow()

    private fun setCheckResult(result: QuestionCheckResult) {
        _checkResult.update { result }
    }


    private fun getQuestionWithAppliedAnswer(): QuestionAndAnswersWrapper.AnswerOptions? {
        val selectedOption = getSelectedOption() ?: return null

        return question.copy(
            answerInput = AnswerInput.Option(id = selectedOption)
        )
    }

    private fun processGivenAnswer(): QuestionCheckResult {
        val isCorrect = getSelectedOption()
            ?.let { question.correctAnswer.checkOption(id = it) }
            ?: false

        return QuestionCheckResult(isCorrect = isCorrect)
    }

    fun checkAnswer(): QuestionWithCheckResult? {
        val checkResult = processGivenAnswer()
        val questionWithAppliedAnswer = getQuestionWithAppliedAnswer() ?: return null

        setCheckResult(checkResult)

        return QuestionWithCheckResult(
            question = questionWithAppliedAnswer, result = checkResult
        )
    }

}