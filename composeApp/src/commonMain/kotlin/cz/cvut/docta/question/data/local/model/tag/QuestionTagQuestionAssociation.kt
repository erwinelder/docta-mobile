package cz.cvut.docta.question.data.local.model.tag

import androidx.room.Entity
import androidx.room.ForeignKey
import cz.cvut.docta.question.data.local.model.entity.QuestionEntity

@Entity(
    tableName = "question_tag_question_association",
    primaryKeys = ["courseCode", "tag", "questionId"],
    foreignKeys = [
        ForeignKey(
            entity = QuestionTagEntity::class,
            parentColumns = ["courseCode", "tag"],
            childColumns = ["courseCode", "tag"],
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
    val courseCode: String,
    val tag: String,
    val questionId: Long
)
