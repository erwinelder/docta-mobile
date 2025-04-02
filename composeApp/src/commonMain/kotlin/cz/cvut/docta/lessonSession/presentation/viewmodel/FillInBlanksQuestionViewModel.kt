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
import cz.cvut.docta.lessonSession.presentation.model.question.QuestionBlankUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FillInBlanksQuestionViewModel(
    private val question: QuestionWrapper.FillInBlanks,
    private val checkAnswerUseCase: CheckAnswerUseCase
) : ViewModel() {

    val materials = question.materials
    val questionUnits = QuestionBlankUnit.fromText(question.question.text)


    private val _blanksAnswers = MutableStateFlow(question.answerInput.answers)
    val blanksAnswers = _blanksAnswers.asStateFlow()

    fun onBlankAnswerChange(blankNumber: Int, answer: String) {
        val newBlanksAnswers = blanksAnswers.value.toMutableMap().apply { put(blankNumber, answer) }
        _blanksAnswers.update { newBlanksAnswers }
    }


    val checkIsAllowed = combine(_blanksAnswers) { blanksAnswersArray ->
        val blanksAnswers = blanksAnswersArray[0]
        blanksAnswers.values.all { it.isNotBlank() }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )


    private val _checkRequestState =
        MutableStateFlow<AnswerCheckRequestState<AnswerCheckResult.Blanks>>(
            AnswerCheckRequestState<AnswerCheckResult.Blanks>.Default(
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
        result as? AnswerCheckResult.Blanks
            ?: return setRequestErrorState(error = LessonSessionError.AnswerCheckError)

        _checkRequestState.update {
            AnswerCheckRequestState.Default(state = AnswerCheckState.Result(result = result))
        }
    }

    private fun setRequestErrorState(error: LessonSessionError) {
        _checkRequestState.update {
            AnswerCheckRequestState.Error<AnswerCheckResult.Blanks>(error = error.toResultState())
        }
    }


    fun checkAnswer() {
        setRequestLoadingState()

        val answerInput = AnswerInput.Blanks(
            questionId = question.question.id, answers = blanksAnswers.value
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
                answerInput = AnswerInputState.Blanks(answers = blanksAnswers.value)
            ),
            isCorrect = checkRequestState.value.isCorrect() ?: return null
        )
    }

}