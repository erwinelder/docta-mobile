package cz.cvut.docta.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.auth.domain.model.UserRole
import cz.cvut.docta.auth.domain.usecase.GetUserDataUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserNameToSecureStorageUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserNameUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserRoleUseCase
import cz.cvut.docta.auth.domain.validation.UserDataValidator
import cz.cvut.docta.auth.mapper.toResultState
import cz.cvut.docta.auth.presentation.model.DataEditingState
import cz.cvut.docta.auth.presentation.model.ProfilePermissions
import cz.cvut.docta.errorHandling.domain.model.result.Result
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import cz.cvut.docta.errorHandling.mapper.toUiState
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ResultWithButtonState
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
    private val saveUserNameToSecureStorageUseCase: SaveUserNameToSecureStorageUseCase,
    private val saveUserRoleUseCase: SaveUserRoleUseCase
) : ViewModel() {

    val permissions = ProfilePermissions.fromViewerAndProfileUserId(
        viewerContext = userContext,
        profileUserId = userId ?: userContext.userId
    )


    private val _userData = MutableStateFlow(UserData())
    val userData = _userData.asStateFlow()

    private fun applyUserData(data: UserData) {
        _userData.update { data }
        _nameEditingState.update { DataEditingState.Idle }
        _roleEditingState.update { DataEditingState.Idle }
        changeName(name = data.name)
        selectRole(role = data.role)
        resetUserDataRequestState()
    }

    private fun fetchUserData(userId: Int) {
        setUserDataRequestLoadingState()

        viewModelScope.launch {
            val result = getUserDataUseCase.execute(userId = userId)

            when (result) {
                is ResultData.Success -> {
                    applyUserData(data = result.data)
                }
                is ResultData.Error -> {
                    setUserDataRequestResultState(result.error.toResultState())
                }
            }
        }
    }


    private val _nameEditingState = MutableStateFlow<DataEditingState>(DataEditingState.Idle)
    val nameEditingState = _nameEditingState.asStateFlow()

    fun toggleNameEditingState() {
        _nameEditingState.update { it.toggle { resetName() } }
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

    fun saveName() {
        if (nameState.value.isNotValid()) return
        val name = nameState.value.fieldText

        viewModelScope.launch {
            _nameEditingState.update { DataEditingState.Saving }

            val result = saveUserNameUseCase.execute(userId = userData.value.id, name = name)
            _nameEditingState.update { DataEditingState.Idle }

            if (result is Result.Success && userId == null) {
                _userData.update { it.copy(name = name) }
                saveUserNameToSecureStorageUseCase.execute(name = name)
            }
        }
    }


    private val _roleEditingState = MutableStateFlow<DataEditingState>(DataEditingState.Idle)
    val roleEditingState = _roleEditingState.asStateFlow()

    fun toggleRoleEditingState() {
        _roleEditingState.update { it.toggle { resetRole() } }
    }

    val availableRoles = UserRole.entries.filter { it != UserRole.Owner }

    private val _roleState = MutableStateFlow<UserRole>(UserRole.User)
    val roleState = _roleState.asStateFlow()

    fun selectRole(role: UserRole) {
        _roleState.update { role }
    }

    fun resetRole() {
        _roleState.update { userData.value.role }
    }

    fun saveRole() {
        val role = roleState.value

        viewModelScope.launch {
            _roleEditingState.update { DataEditingState.Saving }

            val result = saveUserRoleUseCase.execute(userId = userData.value.id, role = role)
            _roleEditingState.update { DataEditingState.Idle }

            if (result is Result.Success) {
                _userData.update { it.copy(role = role) }
            }
        }
    }


    private val _userDataRequestState = MutableStateFlow<RequestState?>(null)
    val userDataRequestState = _userDataRequestState.asStateFlow()

    private fun setUserDataRequestLoadingState() {
        _userDataRequestState.update {
            RequestState.Loading(messageRes = SharedRes.strings.loading_user_data_loader)
        }
    }

    private fun setUserDataRequestResultState(result: ResultWithButtonState) {
        _userDataRequestState.update { RequestState.Result(resultState = result) }
    }

    private fun resetUserDataRequestState() {
        _userDataRequestState.update { null }
    }


    init {
        if (userId != null) {
            fetchUserData(userId = userId)
        } else {
            applyUserData(data = userContext.getUserData())
        }
    }

}