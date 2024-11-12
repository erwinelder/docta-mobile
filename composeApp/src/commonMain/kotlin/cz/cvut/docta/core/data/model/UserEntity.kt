package cz.cvut.docta.core.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user",
    foreignKeys = [
        ForeignKey(
            entity = UserRoleEntity::class,
            parentColumns = ["role"],
            childColumns = ["role"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserEntity(
    @PrimaryKey
    val username: String,
    val role: String
)
