package cz.cvut.docta.answer.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.question.data.model.OpenAnswerQuestionEntity

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
