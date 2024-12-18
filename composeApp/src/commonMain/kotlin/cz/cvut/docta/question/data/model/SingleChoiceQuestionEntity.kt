package cz.cvut.docta.question.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "single_choice_question",
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SingleChoiceQuestionEntity(
    override val id: Long,
    override val text: String,
    val correctAnswerId: Long
) : QuestionEntity(id = id, text = text)
