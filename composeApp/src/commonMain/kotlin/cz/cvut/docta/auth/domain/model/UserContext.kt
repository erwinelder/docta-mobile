package cz.cvut.docta.auth.domain.model

import cz.cvut.docta.auth.domain.usecase.GetAuthTokenFromEncStoreUseCase
import cz.cvut.docta.auth.domain.usecase.SaveAuthTokenToEncStoreUseCase

class UserContext(
    private val saveAuthTokenToEncStoreUseCase: SaveAuthTokenToEncStoreUseCase,
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


    suspend fun setUserData(userData: UserData) {
        this.userId = userData.id
        this.email = userData.email
        this.role = userData.role
        this.name = userData.name

        saveAuthTokenToEncStoreUseCase.execute(token = userData.token)
    }


    fun isAtLeastTeacher(): Boolean = role == UserRole.Teacher || role == UserRole.Admin
    fun isAdmin(): Boolean = role == UserRole.Admin

    suspend fun getAuthToken(): String {
        return getAuthTokenFromEncStoreUseCase.execute()
    }

}
