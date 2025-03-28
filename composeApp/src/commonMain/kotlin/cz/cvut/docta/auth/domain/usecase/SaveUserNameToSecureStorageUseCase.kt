package cz.cvut.docta.auth.domain.usecase

interface SaveUserNameToSecureStorageUseCase {
    suspend fun execute(name: String)
}