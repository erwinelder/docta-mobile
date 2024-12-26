package cz.cvut.docta.section.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.course.data.model.CourseEntity

@Entity(
    tableName = "section",
    foreignKeys = [
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SectionEntity(
    val courseCode: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)