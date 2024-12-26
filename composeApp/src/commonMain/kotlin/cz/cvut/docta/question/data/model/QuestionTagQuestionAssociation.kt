package cz.cvut.docta.question.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "question_tag_question_association",
    primaryKeys = ["tag", "questionId"],
    foreignKeys = [
        ForeignKey(
            entity = QuestionTagEntity::class,
            parentColumns = ["tag"],
            childColumns = ["tag"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class QuestionTagQuestionAssociation(
    val tag: String,
    val questionId: Long
)
