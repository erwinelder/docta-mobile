package cz.cvut.docta.auth.domain.usecase

interface GetAuthTokenFromEncStoreUseCase {
    fun execute(): String
}