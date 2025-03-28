package cz.cvut.docta.auth.mapper

import cz.cvut.docta.auth.data.model.UserDataDto
import cz.cvut.docta.auth.data.model.UserDataWithTokenDto
import cz.cvut.docta.auth.data.model.UserRoleDto
import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.auth.domain.model.UserDataWithToken
import cz.cvut.docta.auth.domain.model.UserRole


fun UserRoleDto.toDomainModel(): UserRole {
    return when (this) {
        UserRoleDto.User -> UserRole.User
        UserRoleDto.Teacher -> UserRole.Teacher
        UserRoleDto.Admin -> UserRole.Admin
        UserRoleDto.Owner -> UserRole.Owner
    }
}

fun UserRole.toDto(): UserRoleDto {
    return when (this) {
        UserRole.User -> UserRoleDto.User
        UserRole.Teacher -> UserRoleDto.Teacher
        UserRole.Admin -> UserRoleDto.Admin
        UserRole.Owner -> UserRoleDto.Owner
    }
}


fun UserDataDto.toDomainModel(): UserData {
    return UserData(
        id = id,
        email = email,
        role = role.toDomainModel(),
        name = name
    )
}


fun UserDataWithTokenDto.toDomainModel(): UserDataWithToken {
    return UserDataWithToken(
        id = id,
        email = email,
        role = role.toDomainModel(),
        name = name,
        token = token
    )
}