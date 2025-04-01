package cz.cvut.docta.auth.mapper

import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result
import cz.cvut.docta.errorHandling.presentation.model.ResultWithButtonState
import dev.icerock.moko.resources.StringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.close_icon
import docta.composeapp.generated.resources.reset_icon
import docta.composeapp.generated.resources.short_arrow_left_icon
import org.jetbrains.compose.resources.DrawableResource


fun Result<AuthSuccess, AuthError>.toResultState(): ResultWithButtonState {
    return when (this) {
        is Result.Success -> this.success.toResultState()
        is Result.Error -> this.error.toResultState()
    }
}


fun AuthSuccess.toResultState(): ResultWithButtonState {
    return ResultWithButtonState(
        isSuccessful = true,
        titleRes = this.asTitleRes(),
        messageRes = this.asMessageRes(),
        buttonTextRes = this.asButtonTextRes(),
        buttonIconRes = this.asButtonIconRes()
    )
}

fun AuthError.toResultState(): ResultWithButtonState {
    return ResultWithButtonState(
        isSuccessful = false,
        titleRes = this.asTitleRes(),
        messageRes = this.asMessageRes(),
        buttonTextRes = this.asButtonTextRes(),
        buttonIconRes = this.asButtonIconRes()
    )
}


private fun AuthSuccess.asTitleRes(): StringResource {
    return when (this) {
        AuthSuccess.SignedIn -> SharedRes.strings.welcome_back_to_docta
        AuthSuccess.EmailVerificationSent -> SharedRes.strings.email_sent
        AuthSuccess.SignedUp -> SharedRes.strings.welcome_to_docta
        AuthSuccess.NameUpdated -> SharedRes.strings.name_updated
        AuthSuccess.RoleUpdated -> SharedRes.strings.role_updated
        AuthSuccess.OwnAccountDeleted -> SharedRes.strings.account_deleted
        AuthSuccess.UserAccountDeleted -> SharedRes.strings.account_deleted
    }
}

private fun AuthSuccess.asMessageRes(): StringResource? {
    return when (this) {
        AuthSuccess.SignedIn -> null
        AuthSuccess.EmailVerificationSent -> SharedRes.strings.sign_up_email_verification_sent_description
        AuthSuccess.SignedUp -> null
        AuthSuccess.NameUpdated -> SharedRes.strings.name_updated
        AuthSuccess.RoleUpdated -> SharedRes.strings.role_updated
        AuthSuccess.OwnAccountDeleted -> SharedRes.strings.own_account_deletion_success_message
        AuthSuccess.UserAccountDeleted -> SharedRes.strings.user_account_deletion_success_message
    }
}

private fun AuthSuccess.asButtonTextRes(): StringResource {
    return when (this) {
        AuthSuccess.SignedIn -> SharedRes.strings.continue_to_app
        AuthSuccess.EmailVerificationSent -> SharedRes.strings.check
        AuthSuccess.SignedUp -> SharedRes.strings.continue_to_app
        AuthSuccess.NameUpdated -> SharedRes.strings.close
        AuthSuccess.RoleUpdated -> SharedRes.strings.close
        AuthSuccess.OwnAccountDeleted -> SharedRes.strings.continue_
        AuthSuccess.UserAccountDeleted -> SharedRes.strings.back
    }
}

private fun AuthSuccess.asButtonIconRes(): DrawableResource? {
    return when (this) {
        AuthSuccess.SignedIn -> null
        AuthSuccess.EmailVerificationSent -> null
        AuthSuccess.SignedUp -> null
        AuthSuccess.NameUpdated -> Res.drawable.close_icon
        AuthSuccess.RoleUpdated -> Res.drawable.close_icon
        AuthSuccess.OwnAccountDeleted -> null
        AuthSuccess.UserAccountDeleted -> Res.drawable.short_arrow_left_icon
    }
}


private fun AuthError.asTitleRes(): StringResource {
    return when (this) {
        AuthError.WrongCredentials -> SharedRes.strings.oops
        AuthError.SignInError -> SharedRes.strings.oops
        AuthError.UserNotFound -> SharedRes.strings.oops
        AuthError.UserAlreadyExists -> SharedRes.strings.oops
        AuthError.EmailVerificationError -> SharedRes.strings.oops
        AuthError.SignUpError -> SharedRes.strings.oops
        AuthError.EmailNotVerifiedError -> SharedRes.strings.oops
        AuthError.UserDataFetchError -> SharedRes.strings.oops
        AuthError.AccountDeletionError -> SharedRes.strings.oops
        AuthError.EmailNotVerifiedYet -> SharedRes.strings.not_verified
        AuthError.NameUpdateError -> SharedRes.strings.name_update_error
        AuthError.RoleUpdateError -> SharedRes.strings.role_update_error
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
        AuthError.NameUpdateError -> SharedRes.strings.name_update_error
        AuthError.RoleUpdateError -> SharedRes.strings.role_update_error
        AuthError.UserDataFetchError -> SharedRes.strings.user_data_not_fetched_error
        AuthError.AccountDeletionError -> SharedRes.strings.account_deletion_error_message
    }
}

private fun AuthError.asButtonTextRes(): StringResource {
    return when (this) {
        AuthError.WrongCredentials -> SharedRes.strings.try_again
        AuthError.SignInError -> SharedRes.strings.try_again
        AuthError.UserNotFound -> SharedRes.strings.back
        AuthError.UserAlreadyExists -> SharedRes.strings.try_again
        AuthError.EmailVerificationError -> SharedRes.strings.try_again
        AuthError.SignUpError -> SharedRes.strings.try_again
        AuthError.EmailNotVerifiedError -> SharedRes.strings.try_again
        AuthError.EmailNotVerifiedYet -> SharedRes.strings.try_again
        AuthError.NameUpdateError -> SharedRes.strings.close
        AuthError.RoleUpdateError -> SharedRes.strings.close
        AuthError.UserDataFetchError -> SharedRes.strings.back
        AuthError.AccountDeletionError -> SharedRes.strings.close
    }
}

private fun AuthError.asButtonIconRes(): DrawableResource? {
    return when (this) {
        AuthError.WrongCredentials -> Res.drawable.reset_icon
        AuthError.SignInError -> Res.drawable.reset_icon
        AuthError.UserNotFound -> Res.drawable.short_arrow_left_icon
        AuthError.UserAlreadyExists -> Res.drawable.reset_icon
        AuthError.EmailVerificationError -> Res.drawable.reset_icon
        AuthError.SignUpError -> Res.drawable.reset_icon
        AuthError.EmailNotVerifiedError -> Res.drawable.reset_icon
        AuthError.EmailNotVerifiedYet -> Res.drawable.reset_icon
        AuthError.NameUpdateError -> Res.drawable.close_icon
        AuthError.RoleUpdateError -> Res.drawable.close_icon
        AuthError.UserDataFetchError -> Res.drawable.short_arrow_left_icon
        AuthError.AccountDeletionError -> Res.drawable.close_icon
    }
}