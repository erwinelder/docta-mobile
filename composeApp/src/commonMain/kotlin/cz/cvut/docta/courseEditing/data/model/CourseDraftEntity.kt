package cz.cvut.docta.courseEditing.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_draft")
data class CourseDraftEntity(
    @PrimaryKey
    val code: String,
    val locale: String,
    val name: String
)