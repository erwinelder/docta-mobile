package cz.cvut.docta.question.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.lesson.data.model.DefaultLessonEntity

@Entity(
    tableName = "question_tag_default_lesson_association",
    primaryKeys = ["tag", "lessonId"],
    foreignKeys = [
        ForeignKey(
            entity = QuestionTagEntity::class,
            parentColumns = ["tag"],
            childColumns = ["tag"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DefaultLessonEntity::class,
            parentColumns = ["lessonId"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class QuestionTagDefaultLessonAssociation(
    val tag: String,
    val lessonId: Long
)
