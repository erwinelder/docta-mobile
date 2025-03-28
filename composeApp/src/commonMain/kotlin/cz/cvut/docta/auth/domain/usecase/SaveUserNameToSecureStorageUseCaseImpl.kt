package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.data.preferences.SecureStorage

class SaveUserNameToSecureStorageUseCaseImpl(
    private val secureStorage: SecureStorage,
    private val userContext: UserContext
) : SaveUserNameToSecureStorageUseCase {
    override suspend fun execute(name: String) {
        secureStorage.saveUserName(name = name)
        userContext.setUserName(name = name)
    }
}