package cz.cvut.docta.lessonSession.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInput
import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCheckResult
import cz.cvut.docta.lessonSession.presentation.model.QuestionAndAnswersWrapper
import cz.cvut.docta.lessonSession.presentation.model.question.QuestionBlankUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FillInBlanksQuestionViewModel(
    private val question: QuestionAndAnswersWrapper.FillInBlanks
) : ViewModel() {

    val questionUnits = QuestionBlankUnit.fromText(question.question.text)
    val questionMaterials = question.materials


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


    private val _checkResult = MutableStateFlow<QuestionCheckResult?>(null)
    val checkResult = _checkResult.asStateFlow()

    private fun setCheckResult(result: QuestionCheckResult) {
        _checkResult.update { result }
    }


    private fun getQuestionWithAppliedAnswer(): QuestionAndAnswersWrapper.FillInBlanks {
        return question.copy(
            answerInput = AnswerInput.Blanks(answers = blanksAnswers.value)
        )
    }

    private fun processGivenAnswer(): QuestionCheckResult {
        val isCorrect = question.correctAnswer
            .getWrongBlanksWithCorrectAnswer(blanksAnswers.value)
            .isEmpty()

        return QuestionCheckResult(isCorrect = isCorrect)
    }

    fun checkAnswers(): QuestionWithCheckResult {
        val checkResult = processGivenAnswer()

        setCheckResult(checkResult)

        return QuestionWithCheckResult(
            question = getQuestionWithAppliedAnswer(),
            result = checkResult
        )
    }

}