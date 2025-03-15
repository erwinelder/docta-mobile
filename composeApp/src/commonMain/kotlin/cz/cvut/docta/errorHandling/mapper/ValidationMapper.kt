package cz.cvut.docta.errorHandling.mapper

import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.domain.model.validation.UserDataValidation
import cz.cvut.docta.errorHandling.domain.model.validation.ValidationResult
import cz.cvut.docta.errorHandling.presentation.model.ValidationUiState


fun List<ValidationResult<UserDataValidation>>.toUiStates(): List<ValidationUiState> {
    return map { it.toUiState() }
}

fun ValidationResult<UserDataValidation>.toUiState(): ValidationUiState {
    val isValid = this is ValidationResult.Success

    return ValidationUiState(
        isValid = isValid,
        messageRes = when (this.validation) {
            UserDataValidation.IsValid -> SharedRes.strings.is_valid
            UserDataValidation.NotValid -> SharedRes.strings.not_valid
            UserDataValidation.RequiredField -> SharedRes.strings.required_field
            UserDataValidation.TooShort -> SharedRes.strings.too_short
            UserDataValidation.TooLong -> SharedRes.strings.too_long

            UserDataValidation.EmailDomainNotFelCvutCz -> SharedRes.strings.email_domain_not_fel_cvut_cz

            UserDataValidation.AtLeastOneUppercaseLetter -> SharedRes.strings.at_least_1_uppercase_letter
            UserDataValidation.AtLeastOneLowercaseLetter -> SharedRes.strings.at_least_1_lowercase_letter
            UserDataValidation.AtLeastOneDigit -> SharedRes.strings.at_least_1_digit
            UserDataValidation.AtLeastOneSpecChar -> SharedRes.strings.at_least_1_spec_char
            UserDataValidation.PasswordsMatching ->
                if (isValid) SharedRes.strings.passwords_do_match else SharedRes.strings.passwords_do_not_match
        }
    )
}