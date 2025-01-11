package cz.cvut.docta.question.data.remote.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

@Entity(
    tableName = "question_remote",
    foreignKeys = [
        ForeignKey(
            entity = CourseRemoteEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionRemoteEntity(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val difficulty: String
)
