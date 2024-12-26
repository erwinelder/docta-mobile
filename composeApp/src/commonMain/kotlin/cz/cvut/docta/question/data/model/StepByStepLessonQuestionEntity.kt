package cz.cvut.docta.question.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.lesson.data.model.StepByStepLessonEntity

@Entity(
    tableName = "step_by_step_lesson_question",
    foreignKeys = [
        ForeignKey(
            entity = StepByStepLessonEntity::class,
            parentColumns = ["lessonId"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class StepByStepLessonQuestionEntity(
    val lessonId: Long?,
    @PrimaryKey
    val id: Long,
    val orderNum: Int,
    val questionText: String,
    val correctAnswerText: String
)
