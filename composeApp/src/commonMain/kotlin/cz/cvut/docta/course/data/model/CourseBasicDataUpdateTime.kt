package cz.cvut.docta.course.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "course_basic_data_update_time",
    foreignKeys = [
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CourseBasicDataUpdateTime(
    @PrimaryKey
    val courseCode: String,
    val lastUpdateTime: Long
)
