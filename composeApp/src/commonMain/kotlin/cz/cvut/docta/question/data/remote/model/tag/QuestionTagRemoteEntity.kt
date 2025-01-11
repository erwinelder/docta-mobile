package cz.cvut.docta.question.data.remote.model.tag

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

@Entity(
    tableName = "question_tag_remote",
    primaryKeys = ["courseCode", "tag"],
    foreignKeys = [
        ForeignKey(
            entity = CourseRemoteEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionTagRemoteEntity(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    val tag: String
)
