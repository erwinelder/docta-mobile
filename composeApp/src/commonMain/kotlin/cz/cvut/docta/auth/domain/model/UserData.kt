package cz.cvut.docta.auth.domain.model

/**
 * Represents user data that comes from the server after successful sign in or sign up.
 * @property token JWT token that is used for requests authorization on the server.
 */
data class UserData(
    val id: Int,
    val email: String,
    val role: UserRole,
    val name: String,
    val token: String
)
