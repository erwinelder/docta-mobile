package cz.cvut.docta.auth.domain.usecase

interface GetAuthTokenFromEncStoreUseCase {
    suspend fun execute(): String
}