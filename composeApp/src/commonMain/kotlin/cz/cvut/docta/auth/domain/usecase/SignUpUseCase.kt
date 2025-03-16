package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

interface SignUpUseCase {
    suspend fun execute(
        name: String,
        email: String,
        password: String
    ): Result<AuthSuccess, AuthError>
}