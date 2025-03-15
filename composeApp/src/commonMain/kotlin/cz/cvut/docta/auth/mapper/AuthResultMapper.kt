package cz.cvut.docta.auth.mapper

import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.presentation.model.ResultUiState
import dev.icerock.moko.resources.StringResource


fun AuthSuccess.toUiState(): ResultUiState {
    return ResultUiState(
        isSuccessful = true,
        titleRes = this.asTitleRes(),
        messageRes = this.asMessageRes()
    )
}

fun AuthError.toUiState(): ResultUiState {
    return ResultUiState(
        isSuccessful = false,
        titleRes = this.asTitleRes(),
        messageRes = this.asMessageRes()
    )
}


private fun AuthSuccess.asTitleRes(): StringResource {
    return when (this) {
        AuthSuccess.EmailVerificationSent -> SharedRes.strings.email_sent
        AuthSuccess.AccountDeleted -> SharedRes.strings.account_deleted
    }
}

private fun AuthSuccess.asMessageRes(): StringResource {
    return when (this) {
        AuthSuccess.EmailVerificationSent -> SharedRes.strings.sign_up_email_verification_sent_description
        AuthSuccess.AccountDeleted -> SharedRes.strings.your_account_has_been_deleted_successfully
    }
}


private fun AuthError.asTitleRes(): StringResource {
    return when (this) {
        AuthError.WrongCredentials,
        AuthError.SignInError,
        AuthError.UserNotFound,
        AuthError.UserAlreadyExists,
        AuthError.EmailVerificationError,
        AuthError.SignUpError,
        AuthError.EmailNotVerified,
        AuthError.DataDeletionError,
        AuthError.AccountDeletionError -> SharedRes.strings.oops
    }
}

private fun AuthError.asMessageRes(): StringResource {
    return when (this) {
        AuthError.WrongCredentials -> SharedRes.strings.wrong_credentials_error
        AuthError.SignInError -> SharedRes.strings.sign_in_error
        AuthError.UserNotFound -> SharedRes.strings.user_not_found_error
        AuthError.UserAlreadyExists -> SharedRes.strings.user_already_exists_error
        AuthError.EmailVerificationError -> SharedRes.strings.email_verification_error
        AuthError.SignUpError -> SharedRes.strings.sign_up_error
        AuthError.EmailNotVerified -> SharedRes.strings.email_not_verified_error
        AuthError.DataDeletionError -> SharedRes.strings.deleting_user_data_error
        AuthError.AccountDeletionError -> SharedRes.strings.deleting_user_account_error
    }
}