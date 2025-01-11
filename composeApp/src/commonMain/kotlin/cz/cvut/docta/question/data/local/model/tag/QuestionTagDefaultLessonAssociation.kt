package cz.cvut.docta.question.data.local.model.tag

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.lesson.data.local.model.entity.DefaultLessonEntity

@Entity(
    tableName = "question_tag_default_lesson_association",
    primaryKeys = ["tag", "lessonId"],
    foreignKeys = [
        ForeignKey(
            entity = QuestionTagEntity::class,
            parentColumns = ["courseCode", "tag"],
            childColumns = ["courseCode", "tag"],
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
    val courseCode: String,
    val tag: String,
    val lessonId: Long
)
