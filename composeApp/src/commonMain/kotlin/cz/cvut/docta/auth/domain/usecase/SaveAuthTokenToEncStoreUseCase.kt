package cz.cvut.docta.auth.domain.usecase

interface SaveAuthTokenToEncStoreUseCase {
    suspend fun execute(token: String)
}