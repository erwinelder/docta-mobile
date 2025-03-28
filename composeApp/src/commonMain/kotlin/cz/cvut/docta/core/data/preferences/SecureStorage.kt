package cz.cvut.docta.core.data.preferences

import com.russhwolf.settings.Settings
import cz.cvut.docta.auth.domain.model.UserRole
import cz.cvut.docta.core.utils.enumValueOrNull

class SecureStorage(
    private val settings: Settings
) {

    companion object {

        private const val USER_ID_KEY = "user_id"
        private const val USER_EMAIL_KEY = "user_email"
        private const val USER_ROLE_KEY = "user_role"
        private const val USER_NAME_KEY = "user_name"
        private const val AUTH_TOKEN_KEY = "auth_token"

    }


    fun getUserId(): Int = settings.getIntOrNull(key = USER_ID_KEY) ?: 0
    fun saveUserId(id: Int) = settings.putInt(key = USER_ID_KEY, value = id)

    fun getUserEmail(): String = settings.getStringOrNull(key = USER_EMAIL_KEY) ?: ""
    fun saveUserEmail(email: String) = settings.putString(key = USER_EMAIL_KEY, value = email)

    fun getUserRole(): UserRole {
        return enumValueOrNull<UserRole>(
            name = settings.getStringOrNull(key = USER_ROLE_KEY) ?: ""
        ) ?: UserRole.User
    }
    fun saveUserRole(role: UserRole) = settings.putString(key = USER_ROLE_KEY, value = role.name)

    fun getUserName(): String = settings.getStringOrNull(key = USER_NAME_KEY) ?: ""
    fun saveUserName(name: String) = settings.putString(key = USER_NAME_KEY, value = name)

    fun getAuthToken(): String = settings.getStringOrNull(key = AUTH_TOKEN_KEY) ?: ""
    fun saveAuthToken(token: String) = settings.putString(key = AUTH_TOKEN_KEY, value = token)


    fun clear() {
        settings.remove(USER_ID_KEY)
        settings.remove(USER_EMAIL_KEY)
        settings.remove(USER_ROLE_KEY)
        settings.remove(USER_NAME_KEY)
        settings.remove(AUTH_TOKEN_KEY)
    }

}