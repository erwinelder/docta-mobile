package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

interface SignInUseCase {
    suspend fun execute(email: String, password: String): Result<AuthSuccess, AuthError>
}