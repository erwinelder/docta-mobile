package cz.cvut.docta.lessonSession.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.CorrectAnswer
import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.presentation.model.QuestionAndAnswersWrapper
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerInput
import cz.cvut.docta.lessonSession.presentation.model.question.CategorizationOptionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CategorizationQuestionViewModel (
    private val question: QuestionAndAnswersWrapper.Categorization
) : ViewModel() {

    val questionText = question.question.text
    val questionMaterials = question.materials

    private val _options = MutableStateFlow(
        question.question.options.map { domainItem ->
            CategorizationOptionUiState(
                id = domainItem.id,
                text = domainItem.text,
                selectedCategoryId = null,
                selectedCategoryName = null
            )
        }
    )
    val options = _options.asStateFlow()

    val categories get() = question.question.categories

    fun onCategorySelect(optionId: Long, categoryId: Long) {
        val categoryName = question.question.categories.find { it.id == categoryId }?.text ?: return

        _options.update { current ->
            current.map {
                if (it.id == optionId) {
                    it.copy(
                        selectedCategoryId = categoryId,
                        selectedCategoryName = categoryName
                    )
                } else it
            }
        }
    }

    val checkIsAllowed = _options
        .map { options ->
            options.all { it.selectedCategoryId != null }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )


    private val _checkResult = MutableStateFlow<QuestionCheckResult?>(null)
    val checkResult = _checkResult.asStateFlow()

    private fun setCheckResult(result: QuestionCheckResult) {
        _checkResult.update { result }
    }
    private fun getQuestionWithAppliedAnswer(): QuestionAndAnswersWrapper.Categorization? {
        val answeredItems = options.value.mapNotNull { uiOption ->
            uiOption.selectedCategoryId?.let { catId -> uiOption.id to catId }
        }
        if (answeredItems.size != options.value.size) return null

        return question.copy(
            answerInput = AnswerInput.CategorizedOptions(
                optionsCategoryToMap = answeredItems.toMap()
            )
        )
    }

    private fun processGivenAnswer(): QuestionCheckResult {
        val userCategories = options.value.associate { uiOption ->
            uiOption.id to uiOption.selectedCategoryId
        }
        val isCorrect = question.correctAnswer
            .checkAllCategories(userCategories)
        return QuestionCheckResult(isCorrect = isCorrect)
    }

    fun checkAnswer(): QuestionWithCheckResult? {
        val checkResult = processGivenAnswer()
        val questionWithAppliedAnswer = getQuestionWithAppliedAnswer() ?: return null

        setCheckResult(checkResult)

        return QuestionWithCheckResult(
            question = questionWithAppliedAnswer,
            result = checkResult
        )
    }
}