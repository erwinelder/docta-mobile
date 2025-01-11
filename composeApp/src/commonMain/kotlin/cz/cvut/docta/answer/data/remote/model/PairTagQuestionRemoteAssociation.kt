package cz.cvut.docta.answer.data.remote.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity
import cz.cvut.docta.question.data.remote.model.entity.QuestionAnswerPairsQuestionRemoteEntity

@Entity(
    tableName = "pair_tag_question_remote_association",
    primaryKeys = ["tag", "questionId"],
    foreignKeys = [
        ForeignKey(
            entity = CourseRemoteEntity::class,
            parentColumns = ["code"],
            childColumns = ["courseCode"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuestionAnswerPairTagRemoteEntity::class,
            parentColumns = ["courseCode", "tag"],
            childColumns = ["courseCode", "tag"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuestionAnswerPairsQuestionRemoteEntity::class,
            parentColumns = ["questionId"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PairTagQuestionRemoteAssociation(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    val tag: String,
    val questionId: Long
)
