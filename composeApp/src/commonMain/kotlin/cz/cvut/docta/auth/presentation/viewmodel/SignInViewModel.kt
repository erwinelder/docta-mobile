package cz.cvut.docta.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.auth.domain.usecase.SignInUseCase
import cz.cvut.docta.auth.domain.validation.UserDataValidator
import cz.cvut.docta.auth.mapper.toUiState
import cz.cvut.docta.errorHandling.domain.model.result.Result
import cz.cvut.docta.errorHandling.mapper.toUiStates
import cz.cvut.docta.errorHandling.presentation.model.ResultUiState
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


    suspend fun signIn(): Boolean {
        val result = signInUseCase.execute(
            email = emailState.value.fieldText,
            password = passwordState.value.fieldText
        )

        if (result is Result.Error) {
            setResultState(result.error.toUiState())
        }

        return result is Result.Success
    }


    private val _resultState = MutableStateFlow<ResultUiState?>(null)
    val resultState = _resultState.asStateFlow()

    private fun setResultState(result: ResultUiState) {
        _resultState.update { result }
    }

    fun resetResultState() {
        _resultState.update { null }
    }

}