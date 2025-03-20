package cz.cvut.docta.question.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.answer.presentation.model.QuestionAnswerPairItemUiState
import cz.cvut.docta.question.presentation.model.QuestionAndAnswersWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionAnswerPairsQuestionViewModel(
    private val question: QuestionAndAnswersWrapper.QuestionAnswerPairs
) : ViewModel() {

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
                // TODO: statistics
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
                // TODO: statistics
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
                _continueButtonEnabled.update { true }
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


    private val _continueButtonEnabled = MutableStateFlow(false)
    val continueButtonEnabled = _continueButtonEnabled.asStateFlow()


    private fun getQuestion(): QuestionAndAnswersWrapper.QuestionAnswerPairs {
        return question
    }

}