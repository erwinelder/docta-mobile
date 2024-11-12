package cz.cvut.docta.question.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "open_answer_question",
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OpenAnswerQuestionEntity(
    override val id: Long,
    override val text: String,
) : QuestionEntity(id = id, text = text)
