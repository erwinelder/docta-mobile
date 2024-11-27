package cz.cvut.docta.materials.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materials_group")
data class MaterialsGroupEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)
