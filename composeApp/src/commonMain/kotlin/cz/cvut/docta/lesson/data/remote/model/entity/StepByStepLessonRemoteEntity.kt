package cz.cvut.docta.lesson.data.remote.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "step_by_step_lesson_remote",
    foreignKeys = [
        ForeignKey(
            entity = LessonRemoteEntity::class,
            parentColumns = ["id"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StepByStepLessonRemoteEntity(
    @PrimaryKey
    val lessonId: Long,
    val description: String
)
