package cz.cvut.docta.auth.presentation.utils

import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.domain.model.UserRole
import dev.icerock.moko.resources.StringResource


fun UserRole.asStringRes(): StringResource {
    return when (this) {
        UserRole.User -> SharedRes.strings.user_role
        UserRole.Teacher -> SharedRes.strings.teacher_role
        UserRole.Admin -> SharedRes.strings.admin_role
    }
}