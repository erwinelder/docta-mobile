package cz.cvut.docta.lessonSession.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerInput
import cz.cvut.docta.lessonSession.domain.usecase.CheckAnswerUseCase
import cz.cvut.docta.lessonSession.mapper.toResultState
import cz.cvut.docta.lessonSession.presentation.model.QuestionWrapper
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInputState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerOptionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnswerOptionsQuestionViewModel(
    private val question: QuestionWrapper.AnswerOptions,
    private val checkAnswerUseCase: CheckAnswerUseCase
) : ViewModel() {

    val materials = question.materials
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


    private val _checkRequestState =
        MutableStateFlow<AnswerCheckRequestState<AnswerCheckResult.SingleOption>>(
            AnswerCheckRequestState<AnswerCheckResult.SingleOption>.Default(
                state = AnswerCheckState.Idle()
            )
        )
    val checkRequestState = _checkRequestState.asStateFlow()

    private fun setRequestLoadingState() {
        _checkRequestState.update {
            AnswerCheckRequestState.Default(state = AnswerCheckState.Loading())
        }
    }

    private fun setRequestResultState(result: AnswerCheckResult) {
        result as? AnswerCheckResult.SingleOption
            ?: return setRequestErrorState(error = LessonSessionError.AnswerCheckError)

        _checkRequestState.update {
            AnswerCheckRequestState.Default(state = AnswerCheckState.Result(result = result))
        }
    }

    private fun setRequestErrorState(error: LessonSessionError) {
        _checkRequestState.update {
            AnswerCheckRequestState.Error<AnswerCheckResult.SingleOption>(
                error = error.toResultState()
            )
        }
    }


    fun checkAnswer() {
        setRequestLoadingState()

        val selectedOptionId = getSelectedOption()
            ?: return setRequestErrorState(error = LessonSessionError.AnswerCheckError)

        val answerInput = AnswerInput.SingleOption(
            questionId = question.question.id, id = selectedOptionId
        )

        viewModelScope.launch {
            val result = checkAnswerUseCase.execute(answerInput = answerInput)
            when (result) {
                is ResultData.Success -> setRequestResultState(result = result.data)
                is ResultData.Error -> setRequestErrorState(error = result.error)
            }
        }
    }

    fun getQuestionWithCheckResult(): QuestionWithCheckResult? {
        return QuestionWithCheckResult(
            question = question.copy(
                answerInput = AnswerInputState.SingleOption(id = getSelectedOption())
            ),
            isCorrect = checkRequestState.value.isCorrect() ?: return null
        )
    }

}