package cz.cvut.docta.core.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.answer.data.model.AnswerEntity
import cz.cvut.docta.question.data.model.QuestionEntity

@Entity(
    tableName = "question_to_answer",
    primaryKeys = ["questionId", "answerId"],
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AnswerEntity::class,
            parentColumns = ["id"],
            childColumns = ["answerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionToAnswerEntity(
    val questionId: Long,
    val answerId: Long
)
