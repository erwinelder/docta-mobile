package cz.cvut.docta.auth.domain.model

import cz.cvut.docta.auth.domain.usecase.GetAuthTokenFromEncStoreUseCase
import cz.cvut.docta.auth.domain.usecase.GetUserDataFromSecureStorageUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserDataToSecureStorageUseCase

class UserContext(
    private val saveUserDataToSecureStorageUseCase: SaveUserDataToSecureStorageUseCase,
    getUserDataFromSecureStorageUseCase: GetUserDataFromSecureStorageUseCase,
    private val getAuthTokenFromEncStoreUseCase: GetAuthTokenFromEncStoreUseCase
) {

    var userId: Int = 0
        private set
    var email: String = ""
        private set
    var role: UserRole = UserRole.User
        private set
    var name: String = ""
        private set


    fun saveUserData(userDataWithToken: UserDataWithToken) {
        this.userId = userDataWithToken.id
        this.email = userDataWithToken.email
        this.role = userDataWithToken.role
        this.name = userDataWithToken.name

        saveUserDataToSecureStorageUseCase.execute(userDataWithToken = userDataWithToken)
    }

    fun isAtLeastTeacher(): Boolean = role == UserRole.Teacher || role == UserRole.Admin
    fun isAdmin(): Boolean = role == UserRole.Admin

    suspend fun getAuthToken(): String {
        return getAuthTokenFromEncStoreUseCase.execute()
    }


    init {

        val userData = getUserDataFromSecureStorageUseCase.execute()
        this.userId = userData.id
        this.email = userData.email
        this.role = userData.role
        this.name = userData.name

    }

}
