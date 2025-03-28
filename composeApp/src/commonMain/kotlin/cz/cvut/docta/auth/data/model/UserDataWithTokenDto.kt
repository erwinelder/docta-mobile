package cz.cvut.docta.auth.data.model

import kotlinx.serialization.Serializable

/**
 * Represents user data that comes from the server after successful sign in or sign up.
 * @property token JWT token that is used for requests authorization on the server.
 */
@Serializable
data class UserDataWithTokenDto(
    val id: Int,
    val email: String,
    val role: UserRoleDto,
    val name: String,
    val token: String
)
