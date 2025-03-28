package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.errorHandling.domain.model.result.Result

class CheckAuthTokenValidityUseCaseImpl(
    private val authRepository: AuthRepository
) : CheckAuthTokenValidityUseCase {
    override suspend fun execute(token: String): Boolean {
        return authRepository.checkTokenValidity(token = token) is Result.Success
    }
}