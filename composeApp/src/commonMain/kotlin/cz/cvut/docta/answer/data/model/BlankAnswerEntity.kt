package cz.cvut.docta.answer.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.cvut.docta.question.data.model.FillInBlanksQuestionEntity

@Entity(
    tableName = "blank_answer",
    foreignKeys = [
        ForeignKey(
            entity = FillInBlanksQuestionEntity::class,
            parentColumns = ["questionId"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BlankAnswerEntity(
    val questionId: Long,
    val blankNum: Int,
    @PrimaryKey
    val id: Long,
    val text: String
)
