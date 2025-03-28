package cz.cvut.docta.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.domain.usecase.DeleteUserAccountUseCase
import cz.cvut.docta.auth.mapper.toResultState
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DeleteUserAccountViewModel(
    private val userId: Int,
    private val deleteUserAccountUseCase: DeleteUserAccountUseCase
) : ViewModel() {

    suspend fun deleteAccount() {
        setRequestLoadingState()

        val result = deleteUserAccountUseCase.execute(userId = userId)
        setRequestResultState(result.toResultState())
    }


    private val _requestState = MutableStateFlow<RequestState?>(null)
    val requestState = _requestState.asStateFlow()

    private fun setRequestLoadingState() {
        _requestState.update {
            RequestState.Loading(messageRes = SharedRes.strings.deleting_account_loader)
        }
    }

    private fun setRequestResultState(result: ResultState) {
        _requestState.update { RequestState.Result(resultState = result) }
    }

    fun resetResultState() {
        _requestState.update { null }
    }

}