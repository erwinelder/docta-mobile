package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

class SignUpUseCaseImpl(
    private val authRepository: AuthRepository
) : SignUpUseCase {
    override suspend fun execute(
        name: String,
        email: String,
        password: String
    ): Result<AuthSuccess, AuthError> {
        return authRepository.signUp(name = name, email = email, password = password)
    }
}