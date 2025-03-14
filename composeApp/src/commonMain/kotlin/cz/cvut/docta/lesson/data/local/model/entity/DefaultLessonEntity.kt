package cz.cvut.docta.lesson.data.local.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "default_lesson",
    foreignKeys = [
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DefaultLessonEntity(
    @PrimaryKey
    val lessonId: Long,
    val defaultLessonType: String,
    val matchAllTags: Boolean
)
