package cz.cvut.docta.lesson.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "step_by_step_lesson",
    foreignKeys = [
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StepByStepLessonEntity(
    @PrimaryKey
    val lessonId: Long,
    val description: String
)
