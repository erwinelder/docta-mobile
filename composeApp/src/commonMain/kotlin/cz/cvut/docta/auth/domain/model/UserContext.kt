package cz.cvut.docta.auth.domain.model

import cz.cvut.docta.auth.domain.usecase.CheckAuthTokenValidityUseCase
import cz.cvut.docta.auth.domain.usecase.GetAuthTokenFromEncStoreUseCase
import cz.cvut.docta.auth.domain.usecase.GetUserDataFromSecureStorageUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserDataToSecureStorageUseCase

class UserContext(
    private val checkAuthTokenValidityUseCase: CheckAuthTokenValidityUseCase,
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


    fun getUserData(): UserData {
        return UserData(
            id = userId,
            email = email,
            role = role,
            name = name
        )
    }

    fun setUserName(name: String) {
        this.name = name
    }

    fun saveUserDataWithToken(data: UserDataWithToken) {
        this.userId = data.id
        this.email = data.email
        this.role = data.role
        this.name = data.name

        saveUserDataToSecureStorageUseCase.execute(userDataWithToken = data)
    }

    fun resetUserData() {
        saveUserDataWithToken(data = UserDataWithToken())
    }

    fun isAtLeastTeacher(): Boolean = role in listOf(UserRole.Teacher, UserRole.Admin, UserRole.Owner)
    fun isAtLeastAdmin(): Boolean = role in listOf(UserRole.Admin, UserRole.Owner)
    fun isOwner(): Boolean = role == UserRole.Owner

    fun getAuthToken(): String {
        return getAuthTokenFromEncStoreUseCase.execute()
    }

    suspend fun checkAuthTokenValidity(): Boolean {
        val isValid = checkAuthTokenValidityUseCase.execute(token = getAuthToken())
        if (!isValid) resetUserData()
        return isValid
    }


    init {

        val userData = getUserDataFromSecureStorageUseCase.execute()
        this.userId = userData.id
        this.email = userData.email
        this.role = userData.role
        this.name = userData.name

    }

}
