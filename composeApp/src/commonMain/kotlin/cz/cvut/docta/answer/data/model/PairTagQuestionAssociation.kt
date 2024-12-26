package cz.cvut.docta.answer.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.question.data.model.QuestionAnswerPairsQuestionEntity

@Entity(
    tableName = "pair_tag_question_association",
    primaryKeys = ["tag", "questionId"],
    foreignKeys = [
        ForeignKey(
            entity = QuestionAnswerPairTagEntity::class,
            parentColumns = ["tag"],
            childColumns = ["tag"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuestionAnswerPairsQuestionEntity::class,
            parentColumns = ["questionId"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PairTagQuestionAssociation(
    val tag: String,
    val questionId: Long
)
