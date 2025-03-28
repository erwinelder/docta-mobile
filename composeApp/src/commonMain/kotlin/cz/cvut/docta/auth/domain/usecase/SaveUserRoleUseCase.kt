package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.domain.model.UserRole
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

interface SaveUserRoleUseCase {
    suspend fun execute(userId: Int, role: UserRole): Result<AuthSuccess, AuthError>
}