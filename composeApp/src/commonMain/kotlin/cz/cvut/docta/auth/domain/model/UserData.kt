package cz.cvut.docta.auth.domain.model

data class UserData(
    val id: Int,
    val email: String,
    val role: UserRole,
    val name: String
)
