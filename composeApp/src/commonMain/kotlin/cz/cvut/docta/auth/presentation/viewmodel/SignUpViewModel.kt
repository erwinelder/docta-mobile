package cz.cvut.docta.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.domain.usecase.CheckEmailVerificationUseCase
import cz.cvut.docta.auth.domain.usecase.SignUpUseCase
import cz.cvut.docta.auth.domain.validation.UserDataValidator
import cz.cvut.docta.auth.mapper.toResultState
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result
import cz.cvut.docta.errorHandling.mapper.toUiStates
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ResultState
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    email: String,
    private val signUpUseCase: SignUpUseCase,
    private val checkEmailVerificationUseCase: CheckEmailVerificationUseCase
) : ViewModel() {

    private val _nameState = MutableStateFlow(
        ValidatedFieldUiState(
            validationStates = UserDataValidator.validateName("").toUiStates()
        )
    )
    val nameState = _nameState.asStateFlow()

    fun updateAndValidateName(name: String) {
        _nameState.update {
            it.copy(
                fieldText = name,
                validationStates = UserDataValidator.validateName(name).toUiStates()
            )
        }
    }


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
            validationStates = UserDataValidator.validatePassword("").toUiStates()
        )
    )
    val passwordState = _passwordState.asStateFlow()

    fun updateAndValidatePassword(password: String) {
        _passwordState.update {
            it.copy(
                fieldText = password,
                validationStates = UserDataValidator.validatePassword(password).toUiStates()
            )
        }
    }


    private val _passwordConfirmationState = MutableStateFlow(
        ValidatedFieldUiState(
            validationStates = UserDataValidator
                .validateConfirmPassword(password = "", confirmPassword = "")
                .toUiStates()
        )
    )
    val passwordConfirmationState = _passwordConfirmationState.asStateFlow()

    fun updateAndValidateConfirmPassword(password: String) {
        _passwordConfirmationState.update {
            it.copy(
                fieldText = password,
                validationStates = UserDataValidator
                    .validateConfirmPassword(
                        password = passwordState.value.fieldText, confirmPassword = password
                    )
                    .toUiStates()
            )
        }
    }


    val signUpIsAllowed = combine(
        nameState, emailState, passwordState, passwordConfirmationState
    ) { nameState, emailState, passwordState, passwordConfirmationState ->
        UserDataValidator.isValidName(name = nameState.fieldText) &&
                UserDataValidator.isValidEmail(email = emailState.fieldText) &&
                UserDataValidator.isValidPassword(password = passwordState.fieldText) &&
                passwordState.fieldText == passwordConfirmationState.fieldText
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = false
    )


    suspend fun signUp(): Boolean {
        setRequestLoadingState(messageRes = SharedRes.strings.creating_your_identity_loader)

        val result = signUpUseCase.execute(
            name = nameState.value.fieldText,
            email = emailState.value.fieldText,
            password = passwordState.value.fieldText
        )

        return when (result) {
            is Result.Success -> true
            is Result.Error -> {
                setRequestResultState(result.error.toResultState())
                false
            }
        }
    }


    private var checkVerificationJob: Job? = null

    fun checkEmailVerification() {
        setRequestLoadingState(messageRes = SharedRes.strings.checking_email_verification_loader)

        checkVerificationJob = viewModelScope.launch {
            val result = checkEmailVerificationUseCase.execute(
                name = nameState.value.fieldText,
                email = emailState.value.fieldText,
                password = passwordState.value.fieldText
            )
            when (result) {
                is Result.Success -> setRequestResultState(
                    result = AuthSuccess.SignedUp.toResultState()
                )
                is Result.Error -> when (result.error) {
                    AuthError.EmailNotVerifiedError -> setRequestResultState(
                        result = AuthError.EmailNotVerifiedYet.toResultState()
                    )
                    else -> setRequestResultState(result.error.toResultState())
                }
            }
        }
    }

    fun cancelEmailVerificationCheck() {
        checkVerificationJob?.cancel()
    }


    private val _requestState = MutableStateFlow<RequestState?>(null)
    val requestState = _requestState.asStateFlow()

    private fun setRequestLoadingState(messageRes: StringResource) {
        _requestState.update {
            RequestState.Loading(messageRes = messageRes)
        }
    }

    private fun setRequestResultState(result: ResultState) {
        _requestState.update { RequestState.Result(resultState = result) }
    }

    fun resetResultState() {
        _requestState.update { null }
    }

}