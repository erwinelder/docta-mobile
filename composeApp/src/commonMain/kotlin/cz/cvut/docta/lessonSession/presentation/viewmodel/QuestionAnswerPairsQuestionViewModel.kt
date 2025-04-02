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
import cz.cvut.docta.lessonSession.presentation.model.answer.QuestionAnswerPairItemUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionAnswerPairsQuestionViewModel(
    private val question: QuestionWrapper.QuestionAnswerPairs,
    private val checkAnswerUseCase: CheckAnswerUseCase
) : ViewModel() {

    val materials = question.materials


    private var hadErrors = false

    private val _questions = MutableStateFlow(question.answerInput.questions)
    val questions = _questions.asStateFlow()

    fun onQuestionSelect(id: Long) {
        answers.value.find { it.isSelected }
            ?.let { selectedAnswer ->
                val isCorrect = id == selectedAnswer.id
                _questions.update {
                    it.markAsCorrectOrIncorrect(id = id, isCorrect = isCorrect)
                }
                _answers.update {
                    it.markAsCorrectOrIncorrect(id = selectedAnswer.id, isCorrect = isCorrect)
                }

                if (!isCorrect) hadErrors = true

                fixateChangesWithDelay()
            }
            ?: _questions.update { it.markAsSelected(id) }
    }


    private val _answers = MutableStateFlow(question.answerInput.answers)
    val answers = _answers.asStateFlow()

    fun onAnswerSelect(id: Long) {
        questions.value.find { it.isSelected }
            ?.let { selectedQuestion ->
                val isCorrect = id == selectedQuestion.id
                _questions.update {
                    it.markAsCorrectOrIncorrect(id = selectedQuestion.id, isCorrect = isCorrect)
                }
                _answers.update {
                    it.markAsCorrectOrIncorrect(id = id, isCorrect = isCorrect)
                }

                if (!isCorrect) hadErrors = true

                fixateChangesWithDelay()
            }
            ?: _answers.update { it.markAsSelected(id) }
    }


    private fun fixateChangesWithDelay() {
        viewModelScope.launch {
            delay(300)

            val newQuestions = questions.value.fixateChanges() ?: return@launch
            val newAnswers = answers.value.fixateChanges() ?: return@launch

            _questions.update { newQuestions }
            _answers.update { newAnswers }

            if (newQuestions.all { it.isDisabled }) {
                finishQuestion()
            }
        }
    }


    private fun List<QuestionAnswerPairItemUiState>.fixateChanges(): List<QuestionAnswerPairItemUiState>? {
        return find { it.isCorrect != null }?.let {
            if (it.isCorrect == true) markAsDisabled(it.id) else reset(it.id)
        }
    }

    private fun List<QuestionAnswerPairItemUiState>.markAsSelected(
        id: Long
    ): List<QuestionAnswerPairItemUiState> {
        return map { item ->
            item.takeIf { it.id == id }?.copy(isSelected = true) ?: item
        }
    }

    private fun List<QuestionAnswerPairItemUiState>.markAsCorrectOrIncorrect(
        id: Long,
        isCorrect: Boolean
    ): List<QuestionAnswerPairItemUiState> {
        return map { item ->
            item.takeIf { it.id == id }?.copy(isSelected = false, isCorrect = isCorrect) ?: item
        }
    }

    private fun List<QuestionAnswerPairItemUiState>.markAsDisabled(
        id: Long
    ): List<QuestionAnswerPairItemUiState> {
        return map { item ->
            item.takeIf { it.id == id }
                ?.copy(isCorrect = null, isSelected = false, isDisabled = true)
                ?: item
        }
    }

    private fun List<QuestionAnswerPairItemUiState>.reset(
        id: Long
    ): List<QuestionAnswerPairItemUiState> {
        return map { item ->
            item.takeIf { it.id == id }
                ?.copy(isCorrect = null, isSelected = false, isDisabled = false)
                ?: item
        }
    }


    private val _checkRequestState =
        MutableStateFlow<AnswerCheckRequestState<AnswerCheckResult.QuestionAnswerPairs>>(
            AnswerCheckRequestState<AnswerCheckResult.QuestionAnswerPairs>.Default(
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
        result as? AnswerCheckResult.QuestionAnswerPairs
            ?: return setRequestErrorState(error = LessonSessionError.AnswerCheckError)

        _checkRequestState.update {
            AnswerCheckRequestState.Default(state = AnswerCheckState.Result(result = result))
        }
    }

    private fun setRequestErrorState(error: LessonSessionError) {
        _checkRequestState.update {
            AnswerCheckRequestState.Error<AnswerCheckResult.QuestionAnswerPairs>(
                error = error.toResultState()
            )
        }
    }


    private suspend fun finishQuestion() {
        setRequestLoadingState()

        val answerInput = AnswerInput.QuestionAnswerPairs(
            questionId = question.question.id, hadErrors = hadErrors
        )

        val result = checkAnswerUseCase.execute(answerInput = answerInput)
        when (result) {
            is ResultData.Success -> setRequestResultState(result = result.data)
            is ResultData.Error -> setRequestErrorState(error = result.error)
        }

        _continueButtonEnabled.update { true }
    }


    private val _continueButtonEnabled = MutableStateFlow(false)
    val continueButtonEnabled = _continueButtonEnabled.asStateFlow()


    fun getQuestionWithCheckResult(): QuestionCheckResult? {
        return QuestionCheckResult(
            question = question.copy(
                answerInput = question.answerInput
            ),
            isCorrect = checkRequestState.value.isCorrect() ?: return null
        )
    }

}