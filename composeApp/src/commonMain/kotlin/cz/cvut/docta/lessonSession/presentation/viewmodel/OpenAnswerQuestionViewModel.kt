package cz.cvut.docta.lessonSession.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.lessonSession.presentation.model.QuestionCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerInput
import cz.cvut.docta.lessonSession.domain.usecase.CheckAnswerUseCase
import cz.cvut.docta.lessonSession.mapper.toResultState
import cz.cvut.docta.lessonSession.presentation.model.QuestionWrapper
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInputState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OpenAnswerQuestionViewModel(
    private val question: QuestionWrapper.OpenAnswer,
    private val checkAnswerUseCase: CheckAnswerUseCase
) : ViewModel() {

    val materials = question.materials
    val questionText = question.question.text


    private val _openAnswer = MutableStateFlow("")
    val openAnswer = _openAnswer.asStateFlow()

    fun onOpenAnswerChange(answer: String) {
        _openAnswer.update { answer }
    }


    val checkIsAllowed = combine(_openAnswer) { answerArray ->
        val answer = answerArray[0]
        answer.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )


    private val _checkRequestState =
        MutableStateFlow<AnswerCheckRequestState<AnswerCheckResult.Open>>(
            AnswerCheckRequestState<AnswerCheckResult.Open>.Default(
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
        result as? AnswerCheckResult.Open
            ?: return setRequestErrorState(error = LessonSessionError.AnswerCheckError)

        _checkRequestState.update {
            AnswerCheckRequestState.Default(state = AnswerCheckState.Result(result = result))
        }
    }

    private fun setRequestErrorState(error: LessonSessionError) {
        _checkRequestState.update {
            AnswerCheckRequestState.Error<AnswerCheckResult.Open>(error = error.toResultState())
        }
    }


    fun checkAnswer() {
        setRequestLoadingState()

        val answerInput = AnswerInput.Open(
            questionId = question.question.id, answer = openAnswer.value
        )

        viewModelScope.launch {
            val result = checkAnswerUseCase.execute(answerInput = answerInput)
            when (result) {
                is ResultData.Success -> setRequestResultState(result = result.data)
                is ResultData.Error -> setRequestErrorState(error = result.error)
            }
        }
    }

    fun getQuestionWithCheckResult(): QuestionCheckResult? {
        return QuestionCheckResult(
            question = question.copy(
                answerInput = AnswerInputState.Open(answer = openAnswer.value)
            ),
            isCorrect = checkRequestState.value.isCorrect() ?: return null
        )
    }

}