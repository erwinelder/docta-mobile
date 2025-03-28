package cz.cvut.docta.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.domain.usecase.SignInUseCase
import cz.cvut.docta.auth.domain.validation.UserDataValidator
import cz.cvut.docta.auth.mapper.toResultState
import cz.cvut.docta.errorHandling.mapper.toUiStates
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ResultState
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SignInViewModel(
    email: String,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _emailState = MutableStateFlow(
        ValidatedFieldUiState(
            fieldText = email,
            validationStates = UserDataValidator.validateEmail(email).toUiStates()
        )
    )
    val emailState = _emailState.asStateFlow()

    fun updateAndValidateEmail(email: String) {
        _emailState.update {
            it.copy(
                fieldText = email,
                validationStates = UserDataValidator.validateEmail(email).toUiStates()
            )
        }
    }


    private val _passwordState = MutableStateFlow(
        ValidatedFieldUiState(
            validationStates = UserDataValidator.validateRequiredFieldIsNotEmpty("").toUiStates()
        )
    )
    val passwordState = _passwordState.asStateFlow()

    fun updateAndValidatePassword(password: String) {
        _passwordState.update {
            it.copy(
                fieldText = password,
                validationStates = UserDataValidator.validateRequiredFieldIsNotEmpty(password)
                    .toUiStates()
            )
        }
    }


    val signInIsAllowed = combine(
        emailState, passwordState
    ) { emailState, passwordState ->
        UserDataValidator.isValidEmail(email = emailState.fieldText) &&
                passwordState.fieldText.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = false
    )


    suspend fun signIn() {
        setRequestLoadingState()

        val result = signInUseCase.execute(
            email = emailState.value.fieldText,
            password = passwordState.value.fieldText
        )

        setRequestResultState(result.toResultState())
    }


    private val _requestState = MutableStateFlow<RequestState?>(null)
    val requestState = _requestState.asStateFlow()

    private fun setRequestLoadingState() {
        _requestState.update {
            RequestState.Loading(messageRes = SharedRes.strings.verifying_your_credentials_loader)
        }
    }

    private fun setRequestResultState(result: ResultState) {
        _requestState.update { RequestState.Result(resultState = result) }
    }

    fun resetResultState() {
        _requestState.update { null }
    }

}