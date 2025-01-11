package cz.cvut.docta.answer.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.question.data.local.model.entity.AnswerOptionsQuestionEntity

@Entity(
    tableName = "answer_option",
    foreignKeys = [
        ForeignKey(
            entity = AnswerOptionsQuestionEntity::class,
            parentColumns = ["questionId"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AnswerOptionEntity(
    val questionId: Long,
    @PrimaryKey
    val id: Long,
    val text: String
)
