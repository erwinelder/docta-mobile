package cz.cvut.docta.answer.data.remote.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

@Entity(
    tableName = "question_answer_pair_tag_remote",
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
data class QuestionAnswerPairTagRemoteEntity(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    val tag: String
)
