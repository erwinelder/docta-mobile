package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.core.data.preferences.SecureStorage

class GetUserDataFromSecureStorageUseCaseImpl(
    private val secureStorage: SecureStorage
) : GetUserDataFromSecureStorageUseCase {
    override fun execute(): UserData {
        val id = secureStorage.getUserId()
        val email = secureStorage.getUserEmail()
        val role = secureStorage.getUserRole()
        val name = secureStorage.getUserName()

        return UserData(id = id, email = email, role = role, name = name)
    }
}