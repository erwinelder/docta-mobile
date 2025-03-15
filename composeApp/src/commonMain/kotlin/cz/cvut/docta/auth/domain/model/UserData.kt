package cz.cvut.docta.auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: Int,
    val email: String,
    val role: UserRole,
    val name: String,
    val token: String
)
