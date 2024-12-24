package cz.cvut.docta.section.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "section")
data class SectionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)