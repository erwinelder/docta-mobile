package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.core.data.preferences.SecureStorage

class GetAuthTokenFromEncStoreUseCaseImpl(
    private val secureStorage: SecureStorage
) : GetAuthTokenFromEncStoreUseCase {
    override fun execute(): String {
        return secureStorage.getAuthToken()
    }
}