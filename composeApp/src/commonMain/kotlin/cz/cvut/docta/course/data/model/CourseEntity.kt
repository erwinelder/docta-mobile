package cz.cvut.docta.course.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class CourseEntity(
    @PrimaryKey
    val code: String,
    val locale: String,
    val name: String
)
