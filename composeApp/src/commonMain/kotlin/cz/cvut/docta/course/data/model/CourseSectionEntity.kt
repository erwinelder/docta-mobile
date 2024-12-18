package cz.cvut.docta.course.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_section")
data class CourseSectionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)
