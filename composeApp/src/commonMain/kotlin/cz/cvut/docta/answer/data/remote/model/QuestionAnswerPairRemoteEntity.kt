package cz.cvut.docta.answer.data.remote.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

@Entity(
    tableName = "question_answer_pair_remote",
    foreignKeys = [
        ForeignKey(
            entity = CourseRemoteEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionAnswerPairRemoteEntity(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val difficulty: String,
    val questionText: String,
    val answerText: String
)
