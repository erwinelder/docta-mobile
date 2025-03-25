package cz.cvut.docta.errorHandling.domain.model.result

enum class AuthSuccess : Success {
    SignedIn,
    EmailVerificationSent,
    SignedUp,
    NameUpdated,
    RoleUpdated,
    AccountDeleted
}