package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.auth.domain.model.UserRole
import cz.cvut.docta.auth.mapper.toDto
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

class SaveUserRoleUseCaseImpl(
    private val authRepository: AuthRepository,
    private val userContext: UserContext
) : SaveUserRoleUseCase {
    override suspend fun execute(userId: Int, role: UserRole): Result<AuthSuccess, AuthError> {
        return authRepository.saveUserRole(
            userId = userId, role = role.toDto(), token = userContext.getAuthToken()
        )
    }
}