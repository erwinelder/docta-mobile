package cz.cvut.docta.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDataDto(
    val id: Int,
    val email: String,
    val role: UserRoleDto,
    val name: String,
    val token: String
)
