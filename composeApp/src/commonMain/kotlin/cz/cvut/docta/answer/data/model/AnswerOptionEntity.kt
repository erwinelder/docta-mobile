package cz.cvut.docta.answer.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.question.data.model.ChooseAnswerQuestionEntity

@Entity(
    tableName = "answer_option",
    foreignKeys = [
        ForeignKey(
            entity = ChooseAnswerQuestionEntity::class,
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
