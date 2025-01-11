package cz.cvut.docta.core.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.course.data.local.model.CourseEntity

@Entity(
    tableName = "local_update_time",
    primaryKeys = ["tableName", "courseCode"],
    foreignKeys = [
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LocalUpdateTime(
    val tableName: String,
    val courseCode: String,
    val updateTime: Long
)
