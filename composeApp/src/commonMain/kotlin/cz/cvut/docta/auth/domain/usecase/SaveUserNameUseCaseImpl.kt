package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

class SaveUserNameUseCaseImpl(
    private val authRepository: AuthRepository,
    private val userContext: UserContext
) : SaveUserNameUseCase {
    override suspend fun execute(userId: Int, name: String): Result<AuthSuccess, AuthError> {
        return authRepository.saveUserName(
            userId = userId, name = name, token = userContext.getAuthToken()
        )
    }
}