package cz.cvut.docta.core.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.lesson.data.model.LessonEntity
import cz.cvut.docta.question.data.model.QuestionEntity

@Entity(
    tableName = "lesson_to_question",
    primaryKeys = ["lessonId", "questionId"],
    foreignKeys = [
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LessonToQuestionEntity(
    val lessonId: Long,
    val questionId: Long
)
