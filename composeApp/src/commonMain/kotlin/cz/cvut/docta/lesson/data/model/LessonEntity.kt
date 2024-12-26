package cz.cvut.docta.lesson.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson")
data class LessonEntity(
    val sectionId: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val type: String,
    val orderNum: Int,
    val name: String,
    val difficulty: String
)
