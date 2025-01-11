package cz.cvut.docta.course.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_remote")
data class CourseRemoteEntity(
    val updateTime: Long,
    val deleted: Boolean,
    @PrimaryKey
    val code: String,
    val locale: String,
    val name: String
)
