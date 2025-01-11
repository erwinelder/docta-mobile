package cz.cvut.docta.question.data.remote.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity
import cz.cvut.docta.lesson.data.remote.model.entity.StepByStepLessonRemoteEntity

@Entity(
    tableName = "step_by_step_lesson_question_remote",
    foreignKeys = [
        ForeignKey(
            entity = CourseRemoteEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StepByStepLessonRemoteEntity::class,
            parentColumns = ["lessonId"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class StepByStepLessonQuestionRemoteEntity(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    val lessonId: Long?,
    @PrimaryKey
    val id: Long,
    val orderNum: Int,
    val questionText: String,
    val correctAnswerText: String
)
