package cz.cvut.docta.auth.mapper

import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.presentation.model.ResultState
import dev.icerock.moko.resources.StringResource


fun AuthSuccess.toResultState(): ResultState {
    return ResultState(
        isSuccessful = true,
        titleRes = this.asTitleRes(),
        messageRes = this.asMessageRes()
    )
}

fun AuthError.toResultState(): ResultState {
    return ResultState(
        isSuccessful = false,
        titleRes = this.asTitleRes(),
        messageRes = this.asMessageRes()
    )
}


private fun AuthSuccess.asTitleRes(): StringResource {
    return when (this) {
        AuthSuccess.SignedIn -> SharedRes.strings.welcome_back_to_docta
        AuthSuccess.EmailVerificationSent -> SharedRes.strings.email_sent
        AuthSuccess.SignedUp -> SharedRes.strings.welcome_to_docta
        AuthSuccess.AccountDeleted -> SharedRes.strings.account_deleted
    }
}

private fun AuthSuccess.asMessageRes(): StringResource {
    return when (this) {
        AuthSuccess.SignedIn -> SharedRes.strings.welcome_back_to_docta
        AuthSuccess.EmailVerificationSent -> SharedRes.strings.sign_up_email_verification_sent_description
        AuthSuccess.SignedUp -> SharedRes.strings.welcome_to_docta
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
        AuthError.EmailNotVerifiedError,
        AuthError.DataDeletionError,
        AuthError.AccountDeletionError -> SharedRes.strings.oops
        AuthError.EmailNotVerifiedYet -> SharedRes.strings.not_verified
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
        AuthError.EmailNotVerifiedError -> SharedRes.strings.email_not_verified_error
        AuthError.EmailNotVerifiedYet -> SharedRes.strings.your_email_not_verified_description
        AuthError.DataDeletionError -> SharedRes.strings.deleting_user_data_error
        AuthError.AccountDeletionError -> SharedRes.strings.deleting_user_account_error
    }
}