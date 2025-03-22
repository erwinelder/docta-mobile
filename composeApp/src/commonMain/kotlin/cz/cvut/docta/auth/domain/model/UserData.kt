package cz.cvut.docta.auth.domain.model

data class UserData(
    val id: Int = 0,
    val email: String = "",
    val role: UserRole = UserRole.User,
    val name: String = ""
)
