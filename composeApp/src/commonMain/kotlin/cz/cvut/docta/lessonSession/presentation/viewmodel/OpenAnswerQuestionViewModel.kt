package cz.cvut.docta.lessonSession.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInput
import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCheckResult
import cz.cvut.docta.lessonSession.presentation.model.QuestionAndAnswersWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class OpenAnswerQuestionViewModel(
    private val question: QuestionAndAnswersWrapper.OpenAnswer
) : ViewModel() {

    val questionText = question.question.text
    val questionMaterials = question.materials

    private val _answerInput = MutableStateFlow("")
    val answerInput = _answerInput.asStateFlow()

    fun onAnswerInputChange(answer: String) {
        _answerInput.update { answer }
    }


    val checkIsAllowed = combine(_answerInput) { answerArray ->
        val answer = answerArray[0]
        answer.isNotEmpty()
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


    private fun getQuestionWithAppliedAnswer(): QuestionAndAnswersWrapper.OpenAnswer {
        return question.copy(
            answerInput = AnswerInput.Open(answer = answerInput.value)
        )
    }

    private fun processGivenAnswer(): QuestionCheckResult {
        val isCorrect = question.correctAnswer.checkAnswer(answerInput.value)

        return QuestionCheckResult(isCorrect = isCorrect)
    }

    fun checkAnswer(): QuestionWithCheckResult {
        val checkResult = processGivenAnswer()

        setCheckResult(checkResult)

        return QuestionWithCheckResult(
            question = getQuestionWithAppliedAnswer(), result = checkResult
        )
    }

}