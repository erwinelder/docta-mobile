package cz.cvut.docta.core.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.answer.data.model.AnswerPairEntity
import cz.cvut.docta.question.data.model.QuestionEntity

@Entity(
    tableName = "answer_pairs_question_to_pair",
    primaryKeys = ["questionId", "pairId"],
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnswerPairEntity::class,
            parentColumns = ["id"],
            childColumns = ["pairId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AnswerPairsQuestionToPairEntity(
    val questionId: Long,
    val pairId: Long
)
