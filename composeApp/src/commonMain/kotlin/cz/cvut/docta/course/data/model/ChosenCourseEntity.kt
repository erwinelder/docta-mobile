package cz.cvut.docta.course.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chosen_course")
data class ChosenCourseEntity(
    @PrimaryKey
    val code: String,
    val orderNum: Int
)