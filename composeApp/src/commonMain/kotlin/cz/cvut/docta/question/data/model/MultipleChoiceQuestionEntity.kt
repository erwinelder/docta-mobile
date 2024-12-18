package cz.cvut.docta.question.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "multiple_choice_question",
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MultipleChoiceQuestionEntity(
    override val id: Long,
    override val text: String,
) : QuestionEntity(id = id, text = text) {
    @PrimaryKey
    val questionId: Long = id
}
