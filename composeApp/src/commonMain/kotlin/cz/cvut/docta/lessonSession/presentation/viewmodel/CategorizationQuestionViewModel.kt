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
import cz.cvut.docta.lessonSession.presentation.model.answer.OptionWithCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategorizationQuestionViewModel (
    private val question: QuestionWrapper.Categorization,
    private val checkAnswerUseCase: CheckAnswerUseCase
) : ViewModel() {

    val materials = question.materials
    val questionText = question.question.text
    val categories = question.question.categories


    private val _optionsWithCategories = MutableStateFlow(
        question.question.options.zip(question.question.categories).map { (option, category) ->
            OptionWithCategory(optionId = option.id, optionText = option.text)
        }
    )
    val optionsWithCategories = _optionsWithCategories.asStateFlow()

    fun selectOptionCategory(option: Long, category: Long) {
        _optionsWithCategories.update { list ->
            list.map { optionWithCategory ->
                optionWithCategory.takeIf { it.optionId != option } ?: optionWithCategory.copy(
                    category = categories.find { it.id == category }
                )
            }
        }
    }

    val checkIsAllowed = _optionsWithCategories.map { list ->
        list.all { it.category != null }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )


    private val _checkRequestState =
        MutableStateFlow<AnswerCheckRequestState<AnswerCheckResult.CategorizedOptions>>(
            AnswerCheckRequestState<AnswerCheckResult.CategorizedOptions>.Default(
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
        result as? AnswerCheckResult.CategorizedOptions
            ?: return setRequestErrorState(error = LessonSessionError.AnswerCheckError)

        _checkRequestState.update {
            AnswerCheckRequestState.Default(state = AnswerCheckState.Result(result = result))
        }
    }

    private fun setRequestErrorState(error: LessonSessionError) {
        _checkRequestState.update {
            AnswerCheckRequestState.Error<AnswerCheckResult.CategorizedOptions>(
                error = error.toResultState()
            )
        }
    }


    fun checkAnswer() {
        setRequestLoadingState()

        val optionToCategoryMap = _optionsWithCategories.value.associate {
            it.getOptionIdWithCategoryIdOrNull()
                ?: return setRequestErrorState(error = LessonSessionError.AnswerCheckError)
        }
        val answerInput = AnswerInput.CategorizedOptions(
            questionId = question.question.id, optionToCategoryMap = optionToCategoryMap
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
        val optionToCategoryMap = _optionsWithCategories.value.associate {
            it.getOptionIdWithCategoryIdOrNull() ?: return null
        }

        return QuestionWithCheckResult(
            question = question.copy(
                answerInput = AnswerInputState.CategorizedOptions(
                    optionToCategoryMap = optionToCategoryMap
                )
            ),
            isCorrect = checkRequestState.value.isCorrect() ?: return null
        )
    }

}