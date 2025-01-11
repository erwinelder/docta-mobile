package cz.cvut.docta.question.data.remote.model.tag

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.lesson.data.remote.model.entity.DefaultLessonRemoteEntity

@Entity(
    tableName = "question_tag_default_lesson_remote_association",
    primaryKeys = ["tag", "lessonId"],
    foreignKeys = [
        ForeignKey(
            entity = QuestionTagRemoteEntity::class,
            parentColumns = ["courseCode", "tag"],
            childColumns = ["courseCode", "tag"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DefaultLessonRemoteEntity::class,
            parentColumns = ["lessonId"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class QuestionTagDefaultLessonRemoteAssociation(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    val tag: String,
    val lessonId: Long
)
