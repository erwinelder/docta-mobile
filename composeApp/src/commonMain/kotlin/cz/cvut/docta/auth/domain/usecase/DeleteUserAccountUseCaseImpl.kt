package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

class DeleteUserAccountUseCaseImpl(
    private val authRepository: AuthRepository,
    private val userContext: UserContext
) : DeleteUserAccountUseCase {
    override suspend fun execute(userId: Int): Result<AuthSuccess, AuthError> {
        return authRepository.deleteUserAccount(userId = userId, token = userContext.getAuthToken())
    }
}