package cz.cvut.docta.auth.domain.validation

import cz.cvut.docta.core.utils.asList
import cz.cvut.docta.errorHandling.domain.model.validation.UserDataValidation
import cz.cvut.docta.errorHandling.domain.model.validation.ValidationResult

typealias UserDataValidationResult = ValidationResult<UserDataValidation>

object UserDataValidator {

    fun isValidName(name: String): Boolean {
        return validateName(name).none { it is ValidationResult.Error }
    }

    fun validateName(name: String): List<UserDataValidationResult> {
        name.atLeastNumberOfChars(2).takeIfError()?.let { return listOf(it) }
        name.atMostNumberOfChars(30).takeIfError()?.let { return listOf(it) }

        return ValidationResult
            .fromValidation(validation = UserDataValidation.IsValid, isValid = true)
            .asList()
    }


    fun isValidEmail(email: String): Boolean {
        return validateEmail(email).none { it is ValidationResult.Error }
    }

    fun validateEmail(email: String): List<UserDataValidationResult> {
        email.requireNotBlank().takeIfError()?.let { return listOf(it) }
        email.requireEmailDomainFelCvutCz()?.let { return listOf(it) }
        return listOf(email.isValidEmail())
    }


    fun isValidPassword(password: String): Boolean {
        return validatePassword(password).none { it is ValidationResult.Error }
    }

    fun validatePassword(password: String): List<UserDataValidationResult> {
        return listOfNotNull(
            password.atMostNumberOfChars(30).takeIfError()
                ?: password.atLeastNumberOfChars(8).takeIfError(),
            password.atLeastOneUppercaseLetter(),
            password.atLeastOneLowercaseLetter(),
            password.atLeastOneDigit(),
            password.atLeastOneSpecChar()
        )
    }


    fun validateConfirmPassword(
        password: String,
        confirmPassword: String
    ): List<UserDataValidationResult> {
        confirmPassword.requireNotBlank().takeIfError()?.let { return listOf(it) }
        return listOf(password.matchPassword(confirmPassword))
    }


    fun validateRequiredFieldIsNotEmpty(value: String): List<UserDataValidationResult> {
        return listOf(value.requireNotBlank())
    }


    private fun String.requireEmailDomainFelCvutCz(): UserDataValidationResult? {
        return ValidationResult.fromValidationIfError(
            validation = UserDataValidation.EmailDomainNotFelCvutCz,
            isValid = substringAfterLast('@') == "fel.cvut.cz"
        )
    }

    private fun String.isValidEmail(): UserDataValidationResult {
        val isValid = substringBefore('@').isNotEmpty()

        return ValidationResult.fromValidation(
            validation = if (isValid) UserDataValidation.IsValid else UserDataValidation.NotValid,
            isValid = isValid
        )
    }

    private fun String.requireNotBlank(): UserDataValidationResult {
        return ValidationResult.fromValidation(
            validation = UserDataValidation.RequiredField,
            isValid = isNotBlank()
        )
    }

    private fun String.atLeastNumberOfChars(number: Int): UserDataValidationResult {
        return ValidationResult.fromValidation(
            validation = UserDataValidation.TooShort,
            isValid = trim().length >= number
        )
    }

    private fun String.atMostNumberOfChars(number: Int): UserDataValidationResult {
        return ValidationResult.fromValidation(
            validation = UserDataValidation.TooLong,
            isValid = trim().length <= number
        )
    }

    private fun String.atLeastOneUppercaseLetter(): UserDataValidationResult {
        return ValidationResult.fromValidation(
            validation = UserDataValidation.AtLeastOneUppercaseLetter,
            isValid = any { it.isUpperCase() }
        )
    }

    private fun String.atLeastOneLowercaseLetter(): UserDataValidationResult {
        return ValidationResult.fromValidation(
            validation = UserDataValidation.AtLeastOneLowercaseLetter,
            isValid = any { it.isLowerCase() }
        )
    }

    private fun String.atLeastOneDigit(): UserDataValidationResult {
        return ValidationResult.fromValidation(
            validation = UserDataValidation.AtLeastOneDigit,
            isValid = any { it.isDigit() }
        )
    }

    private fun String.atLeastOneSpecChar(
        specChars: CharSequence = "@\$!%*?&#_+-"
    ): UserDataValidationResult {
        return ValidationResult.fromValidation(
            validation = UserDataValidation.AtLeastOneSpecChar,
            isValid = any { it in specChars }
        )
    }

    private fun String.matchPassword(password: String): UserDataValidationResult {
        return ValidationResult.fromValidation(
            validation = UserDataValidation.PasswordsMatching,
            isValid = this == password
        )
    }

}