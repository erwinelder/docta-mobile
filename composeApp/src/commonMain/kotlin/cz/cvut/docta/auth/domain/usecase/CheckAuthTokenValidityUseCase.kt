package cz.cvut.docta.auth.domain.usecase

interface CheckAuthTokenValidityUseCase {
    suspend fun execute(token: String): Boolean
}