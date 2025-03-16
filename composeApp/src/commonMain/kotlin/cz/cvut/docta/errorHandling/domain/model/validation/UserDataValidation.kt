package cz.cvut.docta.errorHandling.domain.model.validation

enum class UserDataValidation: Validation {
    IsValid,
    NotValid,
    RequiredField,
    TooShort,
    TooLong,

    EmailDomainNotFelCvutCz,

    AtLeastOneUppercaseLetter,
    AtLeastOneLowercaseLetter,
    AtLeastOneDigit,
    AtLeastOneSpecChar,
    PasswordsMatching
}