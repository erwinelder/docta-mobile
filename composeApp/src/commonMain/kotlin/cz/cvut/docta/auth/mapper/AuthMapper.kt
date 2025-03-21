package cz.cvut.docta.auth.mapper

import cz.cvut.docta.auth.data.model.UserDataDto
import cz.cvut.docta.auth.data.model.UserRoleDto
import cz.cvut.docta.auth.domain.model.UserDataWithToken
import cz.cvut.docta.auth.domain.model.UserRole


fun UserRoleDto.toDomainModel(): UserRole {
    return when (this) {
        UserRoleDto.User -> UserRole.User
        UserRoleDto.Teacher -> UserRole.Teacher
        UserRoleDto.Admin -> UserRole.Admin
    }
}


fun UserDataDto.toDomainModel(): UserDataWithToken {
    return UserDataWithToken(
        id = id,
        email = email,
        role = role.toDomainModel(),
        name = name,
        token = token
    )
}