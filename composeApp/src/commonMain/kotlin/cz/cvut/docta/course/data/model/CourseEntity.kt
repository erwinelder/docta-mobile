package cz.cvut.docta.course.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.core.data.model.LocaleEntity

@Entity(
    tableName = "course",
    foreignKeys = [
        ForeignKey(
            entity = LocaleEntity::class,
            parentColumns = ["code"],
            childColumns = ["locale"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CourseEntity(
    @PrimaryKey
    val code: String,
    val locale: String,
    val name: String
)
