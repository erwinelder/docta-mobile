package cz.cvut.docta.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.auth.domain.usecase.GetUserDataUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserNameToSecureStorageUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserNameUseCase
import cz.cvut.docta.auth.domain.validation.UserDataValidator
import cz.cvut.docta.auth.mapper.toResultState
import cz.cvut.docta.auth.presentation.model.UserNameEditingState
import cz.cvut.docta.errorHandling.domain.model.result.Result
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.errorHandling.mapper.toUiState
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ResultState
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    userContext: UserContext,
    private val userId: Int?,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val saveUserNameToSecureStorageUseCase: SaveUserNameToSecureStorageUseCase
) : ViewModel() {

    private val _userData = MutableStateFlow(UserData())
    val userData = _userData.asStateFlow()

    private fun applyUserData(data: UserData) {
        _userData.update { data }
        _userNameEditingState.update {
            UserNameEditingState.Idle(name = data.name)
        }
        changeName(name = data.name)
        resetRequestState()
    }

    private fun fetchUserData(userId: Int) {
        setRequestLoadingState()

        viewModelScope.launch {
            val result = getUserDataUseCase.execute(userId = userId)

            when (result) {
                is ResultData.Success -> {
                    applyUserData(data = result.data)
                }
                is ResultData.Error -> {
                    setRequestResultState(result.error.toResultState())
                }
            }
        }
    }


    private val _nameState = MutableStateFlow(
        ValidatedFieldUiState(fieldText = userData.value.name, validationStates = emptyList())
    )
    val nameState = _nameState.asStateFlow()

    fun changeName(name: String) {
        _nameState.update {
            ValidatedFieldUiState(
                fieldText = name,
                validationStates = UserDataValidator.validateName(name).mapNotNull {
                    it.takeIfError()?.toUiState()
                }
            )
        }
    }

    fun resetName() {
        _nameState.update {
            ValidatedFieldUiState(fieldText = userData.value.name, validationStates = emptyList())
        }
    }


    private val _userNameEditingState = MutableStateFlow<UserNameEditingState>(
        UserNameEditingState.Idle(name = userData.value.name)
    )
    val userNameEditingState = _userNameEditingState.asStateFlow()

    fun toggleUserNameEditingState() {
        _userNameEditingState.update {
            when (it) {
                is UserNameEditingState.Idle -> UserNameEditingState.Editing
                is UserNameEditingState.Editing -> {
                    resetName()
                    UserNameEditingState.Idle(name = userData.value.name)
                }
                is UserNameEditingState.Saving -> it
            }
        }
    }

    fun saveName() {
        if (nameState.value.isNotValid()) return
        val name = nameState.value.fieldText

        viewModelScope.launch {
            _userNameEditingState.update { UserNameEditingState.Saving }

            val result = saveUserNameUseCase.execute(userId = userData.value.id, name = name)

            when (result) {
                is Result.Success -> {
                    _userNameEditingState.update {
                        UserNameEditingState.Idle(name = name)
                    }
                    if (userId == null) {
                        saveUserNameToSecureStorageUseCase.execute(name = name)
                    }
                }
                is Result.Error -> {
                    _userNameEditingState.update {
                        UserNameEditingState.Idle(name = userData.value.name)
                    }
                }
            }
        }
    }


    private val _requestState = MutableStateFlow<RequestState?>(null)
    val requestState = _requestState.asStateFlow()

    private fun setRequestLoadingState() {
        _requestState.update {
            RequestState.Loading(messageRes = SharedRes.strings.loading_user_data)
        }
    }

    private fun setRequestResultState(result: ResultState) {
        _requestState.update {
            RequestState.Result(resultState = result)
        }
    }

    private fun resetRequestState() {
        _requestState.update { null }
    }


    init {
        if (userId != null) {
            fetchUserData(userId = userId)
        } else {
            applyUserData(data = userContext.getUserData())
        }
    }

}