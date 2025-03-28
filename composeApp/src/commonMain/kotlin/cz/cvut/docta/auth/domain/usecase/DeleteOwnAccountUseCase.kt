package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

interface DeleteOwnAccountUseCase {
    suspend fun execute(password: String): Result<AuthSuccess, AuthError>
}