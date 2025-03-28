package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.domain.model.UserDataWithToken
import cz.cvut.docta.core.data.preferences.SecureStorage

class SaveUserDataToSecureStorageUseCaseImpl(
    private val secureStorage: SecureStorage
) : SaveUserDataToSecureStorageUseCase {
    override fun execute(userDataWithToken: UserDataWithToken) {
        secureStorage.saveUserId(id = userDataWithToken.id)
        secureStorage.saveUserEmail(email = userDataWithToken.email)
        secureStorage.saveUserRole(role = userDataWithToken.role)
        secureStorage.saveUserName(name = userDataWithToken.name)
        secureStorage.saveAuthToken(token = userDataWithToken.token)
    }
}