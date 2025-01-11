package cz.cvut.docta.answer.data.remote.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

@Entity(
    tableName = "pair_tag_pair_remote_association",
    primaryKeys = ["tag", "pairId"],
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
            entity = QuestionAnswerPairRemoteEntity::class,
            parentColumns = ["id"],
            childColumns = ["pairId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PairTagPairRemoteAssociation(
    val updateTime: Long,
    val deleted: Boolean,
    val courseCode: String,
    val tag: String,
    val pairId: Long
)
