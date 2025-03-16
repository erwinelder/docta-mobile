package cz.cvut.docta.auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class UserRole {
    User, Teacher, Admin
}