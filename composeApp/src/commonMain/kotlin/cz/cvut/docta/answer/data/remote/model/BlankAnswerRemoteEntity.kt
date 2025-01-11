package cz.cvut.docta.answer.data.remote.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity
import cz.cvut.docta.question.data.remote.model.entity.FillInBlanksQuestionRemoteEntity

@Entity(
    tableName = "blank_answer_remote",
    foreignKeys = [
        ForeignKey(
            entity = CourseRemoteEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FillInBlanksQuestionRemoteEntity::class,
            parentColumns = ["questionId"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BlankAnswerRemoteEntity(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    val questionId: Long,
    val blankNum: Int,
    @PrimaryKey
    val id: Long,
    val text: String
)
