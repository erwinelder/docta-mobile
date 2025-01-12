package cz.cvut.docta.course_editing.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_draft")
data class CourseDraftEntity(
    @PrimaryKey
    val code: String,
    val locale: String,
    val name: String
)