package cz.cvut.docta.question.data.remote.model.tag

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.question.data.remote.model.entity.QuestionRemoteEntity

@Entity(
    tableName = "question_tag_question_remote_association",
    primaryKeys = ["courseCode", "tag", "questionId"],
    foreignKeys = [
        ForeignKey(
            entity = QuestionTagRemoteEntity::class,
            parentColumns = ["courseCode", "tag"],
            childColumns = ["courseCode", "tag"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuestionRemoteEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class QuestionTagQuestionRemoteAssociation(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    val tag: String,
    val questionId: Long
)
