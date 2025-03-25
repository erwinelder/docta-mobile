package cz.cvut.docta.errorHandling.domain.model.result

enum class AuthError : Error {
    WrongCredentials,
    SignInError,
    UserNotFound,
    UserAlreadyExists,
    EmailVerificationError,
    SignUpError,
    EmailNotVerifiedError,
    EmailNotVerifiedYet,
    DataDeletionError,
    AccountDeletionError,
    NameUpdateError,
    RoleUpdateError,
    UserDataFetchError,
}