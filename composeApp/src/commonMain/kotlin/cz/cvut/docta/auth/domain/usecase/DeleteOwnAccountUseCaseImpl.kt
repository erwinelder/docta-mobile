package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

class DeleteOwnAccountUseCaseImpl(
    private val authRepository: AuthRepository,
    private val userContext: UserContext,
    private val signOutUseCase: SignOutUseCase
) : DeleteOwnAccountUseCase {
    override suspend fun execute(password: String): Result<AuthSuccess, AuthError> {
        return authRepository
            .deleteOwnAccount(
                userId = userContext.userId,
                token = userContext.getAuthToken(),
                email = userContext.email,
                password = password
            )
            .also {
                if (it is Result.Success) signOutUseCase.execute()
            }
    }
}