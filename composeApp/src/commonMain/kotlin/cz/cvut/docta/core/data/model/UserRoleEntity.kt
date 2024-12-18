package cz.cvut.docta.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_role")
data class UserRoleEntity(
    @PrimaryKey
    val role: String
)
