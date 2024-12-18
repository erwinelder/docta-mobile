package cz.cvut.docta.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locale")
data class LocaleEntity(
    @PrimaryKey
    val code: String
)
