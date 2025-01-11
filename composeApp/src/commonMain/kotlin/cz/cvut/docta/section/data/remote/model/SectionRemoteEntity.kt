package cz.cvut.docta.section.data.remote.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

@Entity(
    tableName = "section_remote",
    foreignKeys = [
        ForeignKey(
            entity = CourseRemoteEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SectionRemoteEntity(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)
