package cz.cvut.docta.materials.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materials")
data class MaterialsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String
)
