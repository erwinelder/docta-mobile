package cz.cvut.docta.answer.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "pair_tag_pair_association",
    primaryKeys = ["tag", "pairId"],
    foreignKeys = [
        ForeignKey(
            entity = QuestionAnswerPairTagEntity::class,
            parentColumns = ["courseCode", "tag"],
            childColumns = ["courseCode", "tag"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuestionAnswerPairEntity::class,
            parentColumns = ["id"],
            childColumns = ["pairId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PairTagPairAssociation(
    val courseCode: String,
    val tag: String,
    val pairId: Long
)
