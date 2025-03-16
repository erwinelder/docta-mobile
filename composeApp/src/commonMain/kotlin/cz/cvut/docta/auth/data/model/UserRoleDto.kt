package cz.cvut.docta.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class UserRoleDto {
    User, Teacher, Admin
}