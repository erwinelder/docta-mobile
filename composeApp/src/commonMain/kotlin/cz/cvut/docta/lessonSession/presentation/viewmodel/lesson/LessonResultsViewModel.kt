package cz.cvut.docta.lessonSession.presentation.viewmodel.lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ResultWithButtonState
import cz.cvut.docta.lessonSession.domain.usecase.DeleteLessonSessionUseCase
import cz.cvut.docta.lessonSession.domain.usecase.FinishLessonSessionUseCase
import cz.cvut.docta.lessonSession.mapper.toResultWithButtonState
import cz.cvut.docta.lessonSession.presentation.model.LessonStatsUiState
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LessonResultsViewModel(
    private val finishLessonSessionUseCase: FinishLessonSessionUseCase,
    private val deleteLessonSessionUseCase: DeleteLessonSessionUseCase
) : ViewModel() {

    private val _lessonStats = MutableStateFlow<LessonStatsUiState?>(null)
    val lessonStats = _lessonStats.asStateFlow()

    fun finishSession() {
        setRequestLoadingState(messageRes = SharedRes.strings.wrapping_up_your_results)

        viewModelScope.launch {
            val result = finishLessonSessionUseCase.execute()
            when (result) {
                is ResultData.Success -> {
                    _lessonStats.update {
                        LessonStatsUiState.fromStats(stats = result.data)
                    }
                    resetRequestState()
                }
                is ResultData.Error -> {
                    setRequestResultState(result = result.error.toResultWithButtonState())
                }
            }
        }
    }


    fun deleteSession() {
        setRequestLoadingState(messageRes = SharedRes.strings.deleting_your_results)

        viewModelScope.launch {
            deleteLessonSessionUseCase.execute()
            resetRequestState()
        }
    }


    private val _requestState = MutableStateFlow<RequestState?>(null)
    val requestState = _requestState.asStateFlow()

    private fun setRequestLoadingState(messageRes: StringResource) {
        _requestState.update {
            RequestState.Loading(messageRes = messageRes)
        }
    }

    private fun setRequestResultState(result: ResultWithButtonState) {
        _requestState.update { RequestState.Result(resultState = result) }
    }

    fun resetRequestState() {
        _requestState.update { null }
    }

}