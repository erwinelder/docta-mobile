package cz.cvut.docta.lesson.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson")
data class LessonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)
