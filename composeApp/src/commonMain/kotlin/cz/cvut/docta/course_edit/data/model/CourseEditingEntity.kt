package cz.cvut.docta.course_edit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_editing")
data class CourseEditingEntity(
    @PrimaryKey
    val code: String,
    val locale: String,
    val name: String
)