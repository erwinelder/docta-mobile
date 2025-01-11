package cz.cvut.docta.answer.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.question.data.local.model.entity.OpenAnswerQuestionEntity

@Entity(
    tableName = "correct_open_answer",
    foreignKeys = [
        ForeignKey(
            entity = OpenAnswerQuestionEntity::class,
            parentColumns = ["questionId"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CorrectOpenAnswerEntity(
    val questionId: Long,
    @PrimaryKey
    val id: Long,
    val text: String
)
