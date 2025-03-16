package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.core.data.preferences.SecureStorage

class GetAuthTokenFromEncStoreUseCaseImpl(
    private val secureStorage: SecureStorage
) : GetAuthTokenFromEncStoreUseCase {
    override suspend fun execute(): String {
        return secureStorage.getAuthToken()
    }
}