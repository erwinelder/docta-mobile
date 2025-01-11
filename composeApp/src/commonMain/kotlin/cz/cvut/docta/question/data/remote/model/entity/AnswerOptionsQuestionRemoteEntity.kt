package cz.cvut.docta.question.data.remote.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "answer_options_question_remote",
    foreignKeys = [
        ForeignKey(
            entity = QuestionRemoteEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AnswerOptionsQuestionRemoteEntity(
    @PrimaryKey
    val questionId: Long,
    val text: String,
    val correctOptionId: Long?
)
